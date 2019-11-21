package com.naufalprakoso.storybook.ui.story.add.image

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Const
import com.naufalprakoso.storybook.model.Frame
import com.naufalprakoso.storybook.model.Story
import com.naufalprakoso.storybook.model.User
import kotlinx.android.synthetic.main.activity_result_image.*
import java.io.FileNotFoundException
import java.util.*
import android.graphics.*
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.ByteArrayOutputStream

class ResultImageActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userId: String
    private var imgPath: Uri? = null
    private lateinit var uploadedImage: String

    private lateinit var uploadedBitmap: Bitmap
    private lateinit var frameBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_image)

        val sendSMSPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (sendSMSPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1
            )
        }

        val frame = intent?.getParcelableExtra<Frame>(Const.FRAME_KEY)
        userId = FirebaseAuth.getInstance().currentUser?.uid!!

        Glide.with(this).load(frame?.image)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean = false

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    img_result.isDrawingCacheEnabled = true
                    frameBitmap = img_result.drawingCache
                    return false
                }
            })
            .into(img_result)
        initialLayout()

        btn_choose_image.setOnClickListener(this)
        btn_add_story.setOnClickListener(this)
        btn_back.setOnClickListener(this)
        btn_upload_image.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> finish()
            R.id.btn_choose_image -> {
                val iImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(iImg, 0)
            }
            R.id.btn_upload_image -> {
                if (imgPath == null) {
                    Toast.makeText(
                        this, "You must choose an image",
                        Toast.LENGTH_SHORT
                    ).show()

                    initialLayout()
                } else {
                    val randomUUID = UUID.randomUUID()
                    val mStorageRef = FirebaseStorage.getInstance().getReference("stories")
                    val imageRef = mStorageRef.child("$userId/$randomUUID")
                    val img = getImageUri(randomUUID)

                    imageRef.putFile(img)
                        .addOnSuccessListener {
                            imageRef.downloadUrl.addOnSuccessListener {
                                uploadedImage = it.toString()
                                finalLayout()
                            }
                        }
                        .addOnFailureListener {
                            println("Info File : ${it.message}")
                        }
                }
            }
            R.id.btn_add_story -> {
                val currentTime = Calendar.getInstance().time
                val title = edt_title.text.toString()

                when {
                    title.isEmpty() -> edt_title.error =
                        getString(R.string.validation_must_be_filled)
                    imgPath == null -> {
                        Toast.makeText(
                            this, "You must choose an image",
                            Toast.LENGTH_SHORT
                        ).show()

                        initialLayout()
                    }
                    else -> {
                        FirebaseFirestore.getInstance().collection("users")
                            .whereEqualTo("id", userId).limit(1).get()
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    val user =
                                        it.result?.documents?.get(0)?.toObject(User::class.java)
                                            ?: User()
                                    val story =
                                        Story(
                                            datetime = currentTime.toString(),
                                            userId = userId,
                                            title = title,
                                            username = user.username,
                                            featuredImage = uploadedImage
                                        )

                                    FirebaseFirestore.getInstance()
                                        .collection("stories").document()
                                        .set(story)
                                        .addOnCompleteListener {
                                            Toast.makeText(
                                                this, "Successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            finish()
                                        }
                                }
                            }
                    }
                }
            }
        }
    }

    private fun initialLayout() {
        btn_add_story.visibility = View.GONE
        card_edt_title.visibility = View.INVISIBLE
        btn_choose_image.visibility = View.VISIBLE
        btn_upload_image.visibility = View.GONE
    }

    private fun processLayout() {
        btn_add_story.visibility = View.GONE
        card_edt_title.visibility = View.INVISIBLE
        btn_choose_image.visibility = View.GONE
        btn_upload_image.visibility = View.VISIBLE
    }

    private fun finalLayout() {
        btn_add_story.visibility = View.VISIBLE
        card_edt_title.visibility = View.VISIBLE
        btn_choose_image.visibility = View.GONE
        btn_upload_image.visibility = View.GONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val targetUri = data?.data
            imgPath = data?.data
            try {
                uploadedBitmap =
                    BitmapFactory.decodeStream(contentResolver.openInputStream(targetUri!!))
                img_upload.setImageBitmap(uploadedBitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            processLayout()
        }
    }

    private fun mergeImage(): Bitmap {
        val bmp1 = uploadedBitmap
        val bmp2 = frameBitmap

        val bmOverlay = Bitmap.createBitmap(bmp1.width, bmp1.height, bmp1.config)
        val canvas = Canvas(bmOverlay)
        canvas.drawBitmap(bmp1, Matrix(), null)
        canvas.drawBitmap(bmp2, 0f, 0f, null)

        return bmOverlay
    }

    private fun getImageUri(uuid: UUID): Uri {
        val bytes = ByteArrayOutputStream()
        val inImage = mergeImage()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        val path =
            MediaStore.Images.Media.insertImage(
                this.contentResolver,
                inImage,
                uuid.toString(),
                null
            )

        return Uri.parse(path)
    }
}
