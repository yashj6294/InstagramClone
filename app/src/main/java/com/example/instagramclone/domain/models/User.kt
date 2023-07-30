package com.example.instagramclone.domain.models

data class User(
    var userName: String = "",
    var name: String = "",
    var userId: String = "",
    var email: String = "",
    var profileUrl: String = "",
    var following : List<String> = emptyList(),
    var followers : List<String> = emptyList(),
    var totalPosts : String = "",
    var bio : String = "",
    var url : String = "",
)
