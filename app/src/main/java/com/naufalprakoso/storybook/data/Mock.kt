package com.naufalprakoso.storybook.data

import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Mock {
    companion object {
        fun insertToMyStory1() {
            val currentTime = Calendar.getInstance().time

            val comments = ArrayList<HashMap<String, String>>()
            val comment = hashMapOf(
                "userId" to "vwVK464POCTieYsn8ZvT9PbNvSY2",
                "comment" to "Cool!",
                "datetime" to currentTime.toString()
            )
            comments.add(comment)

            val images = ArrayList<HashMap<String, String>>()
            val image1 = hashMapOf(
                "image" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory1.png?alt=media&token=7594be02-2d75-4f61-9bdc-006b64ee07c4"
            )
            val image2 = hashMapOf(
                "image" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory2.png?alt=media&token=1ba43fbd-b2bf-4033-bbd1-4be7de901588"
            )
            val image3 = hashMapOf(
                "image" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory3.png?alt=media&token=9038f96a-1c3b-4382-a776-52103365069b"
            )
            images.add(image1)
            images.add(image2)
            images.add(image3)

            val story = hashMapOf(
                "title" to "Test 1",
                "featuredImage" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory1.png?alt=media&token=7594be02-2d75-4f61-9bdc-006b64ee07c4",
                "images" to images,
                "datetime" to currentTime.toString(),
                "likes" to 0,
                "comments" to comments,
                "userId" to "FcRnFpPAe0cNf9J4FinXdDhDqBj2"
            )

            FirebaseFirestore.getInstance().collection("stories")
                .add(story)
                .addOnCompleteListener {
                    println("LogMock: Success 1")
                }
        }

        fun insertToMyStory2() {
            val currentTime = Calendar.getInstance().time

            val comments = ArrayList<HashMap<String, String>>()
            val comment = hashMapOf(
                "userId" to "vwVK464POCTieYsn8ZvT9PbNvSY2",
                "comment" to "WOW!",
                "datetime" to currentTime.toString()
            )
            comments.add(comment)

            val images = ArrayList<HashMap<String, String>>()

            val story = hashMapOf(
                "title" to "Test 2",
                "featuredImage" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory4.jpg?alt=media&token=3e9071bd-174f-40d3-9780-5fdf04be1816",
                "images" to images,
                "datetime" to currentTime.toString(),
                "likes" to 0,
                "comments" to comments,
                "userId" to "FcRnFpPAe0cNf9J4FinXdDhDqBj2"
            )

            FirebaseFirestore.getInstance().collection("stories")
                .add(story)
                .addOnCompleteListener {
                    println("LogMock: Success 2")
                }
        }

        fun insertToMyStory3() {
            val currentTime = Calendar.getInstance().time

            val comments = ArrayList<HashMap<String, String>>()
            val images = ArrayList<HashMap<String, String>>()

            val story = hashMapOf(
                "title" to "Test 3",
                "featuredImage" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory3.png?alt=media&token=9038f96a-1c3b-4382-a776-52103365069b",
                "images" to images,
                "datetime" to currentTime.toString(),
                "likes" to 0,
                "comments" to comments,
                "userId" to "FcRnFpPAe0cNf9J4FinXdDhDqBj2"
            )

            FirebaseFirestore.getInstance().collection("stories")
                .add(story)
                .addOnCompleteListener {
                    println("LogMock: Success 3")
                }
        }
    }
}