package com.naufalprakoso.storybook.data

import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Mock {
    companion object {
        fun insertToMyStory1() {
            val currentTime = Calendar.getInstance().time

            val comment = hashMapOf(
                "userId" to "vwVK464POCTieYsn8ZvT9PbNvSY2",
                "comment" to "Cool!",
                "datetime" to currentTime.toString(),
                "username" to "prakoso"
            )

            val image1 = hashMapOf(
                "image" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory1.png?alt=media&token=7594be02-2d75-4f61-9bdc-006b64ee07c4"
            )
            val image2 = hashMapOf(
                "image" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory2.png?alt=media&token=1ba43fbd-b2bf-4033-bbd1-4be7de901588"
            )
            val image3 = hashMapOf(
                "image" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory3.png?alt=media&token=9038f96a-1c3b-4382-a776-52103365069b"
            )

            val story = hashMapOf(
                "title" to "Test 1",
                "featuredImage" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory1.png?alt=media&token=7594be02-2d75-4f61-9bdc-006b64ee07c4",
                "datetime" to currentTime.toString(),
                "likes" to 0,
                "userId" to "FcRnFpPAe0cNf9J4FinXdDhDqBj2",
                "username" to "naufalprakoso"
            )

            val data = FirebaseFirestore.getInstance().collection("stories").document()
            data.set(story)
            data.collection("comments").add(comment)
            data.collection("images").add(image1)
            data.collection("images").add(image2)
            data.collection("images").add(image3)
        }

        fun insertToMyStory2() {
            val currentTime = Calendar.getInstance().time

            val comments = ArrayList<HashMap<String, String>>()
            val comment = hashMapOf(
                "userId" to "vwVK464POCTieYsn8ZvT9PbNvSY2",
                "comment" to "WOW!",
                "datetime" to currentTime.toString(),
                "username" to "prakoso"
            )
            comments.add(comment)

            val story = hashMapOf(
                "title" to "Test 2",
                "featuredImage" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory4.jpg?alt=media&token=3e9071bd-174f-40d3-9780-5fdf04be1816",
                "datetime" to currentTime.toString(),
                "likes" to 0,
                "userId" to "FcRnFpPAe0cNf9J4FinXdDhDqBj2",
                "username" to "naufalprakoso"
            )

            val data = FirebaseFirestore.getInstance().collection("stories").document()
            data.set(story)
            data.collection("comments").add(comment)
        }

        fun insertToMyStory3() {
            val currentTime = Calendar.getInstance().time

            val story = hashMapOf(
                "title" to "Test 3",
                "featuredImage" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory3.png?alt=media&token=9038f96a-1c3b-4382-a776-52103365069b",
                "datetime" to currentTime.toString(),
                "likes" to 0,
                "userId" to "FcRnFpPAe0cNf9J4FinXdDhDqBj2",
                "username" to "naufalprakoso"
            )

            val data = FirebaseFirestore.getInstance().collection("stories").document()
            data.set(story)
        }

        fun insertToMyStory4() {
            val currentTime = Calendar.getInstance().time

            val story = hashMapOf(
                "title" to "Test 4",
                "featuredImage" to "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/stories%2FFcRnFpPAe0cNf9J4FinXdDhDqBj2%2Fstory3.png?alt=media&token=9038f96a-1c3b-4382-a776-52103365069b",
                "datetime" to currentTime.toString(),
                "likes" to 0,
                "userId" to "vwVK464POCTieYsn8ZvT9PbNvSY2",
                "username" to "prakoso"
            )

            val data = FirebaseFirestore.getInstance().collection("stories").document()
            data.set(story)
        }
    }
}