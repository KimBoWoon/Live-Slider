package com.bowoon.android.live_slider.model

import com.google.gson.annotations.SerializedName

data class OGTag(
    @SerializedName("og:type")
    val type: String,
    @SerializedName("og:url")
    val url: String,
    @SerializedName("og:title")
    val title: String,
    @SerializedName("og:description")
    val description: String,
    @SerializedName("og:image")
    val image: String,
    @SerializedName("og:site_name")
    val siteName: String,
    @SerializedName("og:locale")
    val locale: String,
    @SerializedName("article:author")
    val author: String,
    @SerializedName("article:publisher")
    val publisher: String
)

/*
<meta property="fb:app_id" content="1011513095546498" />
    <meta property="fb:pages" content="306376056509633" />
    <meta property="og:type" content="article" />
    <meta property="og:url" content="https://news.joins.com/article/23514712" />
	<meta property="og:title" content="[속보] 문 대통령, 北 목선 관련 안보실 1차장 '엄중 경고'" />
	<meta property="og:description" content="문재인 대통령. [사진 청와대 제공]                  [속보] 문 대통령, 北 목선 관련 안보실 1차장 '엄중 경고'" />
	<meta property="og:image" content="https://pds.joins.com/news/component/htmlphoto_mmdata/201907/03/7a808145-5789-449e-9e7b-29bc4023cade.jpg" />
    <meta property="og:site_name" content="중앙일보" />
    <meta property="og:locale" content="ko_KR" />
    <meta property="article:author" content="https://www.facebook.com/joongang" />
    <meta property="article:publisher" content="https://www.facebook.com/joongang" />

*/