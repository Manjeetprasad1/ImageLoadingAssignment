package com.manjeet.imageloadingassignment.model

import com.google.gson.annotations.SerializedName

data class MediaCoverageResponse(

	@field:SerializedName("MediaCoverageResponse")
	val mediaCoverageResponse: List<MediaCoverageResponseItem?>? = null
)

data class Thumbnail(

	@field:SerializedName("basePath")
	val basePath: String? = null,

	@field:SerializedName("domain")
	val domain: String? = null,

	@field:SerializedName("qualities")
	val qualities: List<Int?>? = null,

	@field:SerializedName("aspectRatio")
	val aspectRatio: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("version")
	val version: Int? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class MediaCoverageResponseItem(

	@field:SerializedName("publishedBy")
	val publishedBy: String? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("coverageURL")
	val coverageURL: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("mediaType")
	val mediaType: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("backupDetails")
	val backupDetails: BackupDetails? = null
)

data class BackupDetails(

	@field:SerializedName("screenshotURL")
	val screenshotURL: String? = null,

	@field:SerializedName("pdfLink")
	val pdfLink: String? = null
)
