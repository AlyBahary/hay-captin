package com.beetleware.hayatiDeliveryMan.common


object Constants {

    //numbers
    const val CACHE_SIZE = 10 * 1024 * 1024 //10MB
    const val CACHE_MAX_AGE = 5 //5 seconds
    const val CACHE_MAX_STALE = 60 * 60 * 24 * 7 //1 week
    const val GLOBAL="global"
    const val ARABIC = 1
    const val ENGLISH = 0
    const val NOT_DEFINED_LANG = -1

    //time
    const val SNAK_BAR_DURATION: Int = 3 * 1000

    //strings
    const val NETWORKING_LOG = "networking"

    //shared preference keys
    const val PREFERENCE_NAME = "CREP_SHARED_PREFERENCE"

    const val CURRENT_LANGUAGE_KEY = "CURRENT_LANGUAGE"

    const val GALLERY_REQUEST_CODE: Int = 98
    const val CAMERA_REQUEST_CODE: Int = 97

    // Shard Logging

    const val IS_USER_LOGGED_IN_KEY = "IS_USER_LOGGED_IN"

    const val USER_ID_KEY = "USER_ID_NUM"
    const val USER_TYPE_KEY = "USER_ID"
    const val USER_ACCESS_TOKEN_KEY = "USER_ACCESS_TOKEN"
    const val USER_PROFILE_IS_COMPLETED_KEY = "USER_PROFILE_IS_COMPLETED"
    const val USER_PROFILE_IS_CONFIRMED_KEY = "USER_PROFILE_IS_CONFIRMED"
    const val TARGET_SCREEN="USER_TARGET_SCREEN"

    // Patient
    const val PATIENT="PATIENT"
    const val server_Auth = "Basic SGFraW1BcHA6cGFzc3dvcmQ="

    const val REQUEST_CAMERA_PERMISSION_CODE: Int = 96
    const val REQUEST_STORAGE_PERMISSION_CODE: Int = 95
    const val REQUEST_LOCATION_PERMISSION_CODE: Int = 94
    const val LOCATION_REQUEST_CODE: Int = 93

    // OpenTox
     val  API_KEY: String? = "46759232"
     var  SESSION_ID = ""
     var  TOKEN = ""
     const val RC_SETTINGS_SCREEN_PERM = 123
     const val RC_VIDEO_APP_PERM = 124

    // paging
    const val ROWS_NUM = 10
    const val INITIAL_LOAD_SIZE_HINT = 13

    //
    const val ORDER="ORDER"
    const val SUBSCRIPTION="SUBSCRIPTION"
}