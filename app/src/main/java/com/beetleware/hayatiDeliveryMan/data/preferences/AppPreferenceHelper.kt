package com.beetleware.hayatiDeliveryMan.data.preferences

import android.content.SharedPreferences
import com.beetleware.hayatiDeliveryMan.common.Constants
import com.beetleware.hayatiDeliveryMan.common.extensions.get
import com.beetleware.hayatiDeliveryMan.common.extensions.put
import com.beetleware.hayatiDeliveryMan.common.extensions.remove
import com.beetleware.hayatiDeliveryMan.data.network.model.AuthResponse
import javax.inject.Inject

class AppPreferenceHelper @Inject constructor(private val preferences: SharedPreferences) :
    PreferenceHelper {


    override fun setUserLanguage(language: Int) =
        preferences.put(Constants.CURRENT_LANGUAGE_KEY, language)

    override fun getUserLanguage() = preferences.get(
        Constants.CURRENT_LANGUAGE_KEY, Constants.NOT_DEFINED_LANG
    )

    override fun storeUser(user: AuthResponse) {
        setUserLoggedIn(true)
        setUserAccessToken("Bearer "+user.token)
    }


    override fun removeUser() {
        setUserLoggedIn(false)
        preferences.remove(Constants.USER_ACCESS_TOKEN_KEY)
        preferences.remove(Constants.USER_ID_KEY)
        preferences.remove(Constants.USER_PROFILE_IS_COMPLETED_KEY)
        preferences.remove(Constants.USER_PROFILE_IS_CONFIRMED_KEY)
    }


    override fun isUserLoggedIn(): Boolean {
        return preferences.get(Constants.IS_USER_LOGGED_IN_KEY, false)
    }

    /**
     * use this method to store login state of the user
     *
     * @param loginState the login state of the user
     */
    private fun setUserLoggedIn(loginState: Boolean) {
        preferences.put(Constants.IS_USER_LOGGED_IN_KEY, loginState)
    }

    override fun getUserId(): Int {
        return preferences.get(Constants.USER_ID_KEY, 0)
    }

    override fun getUserType(): Int {
        return preferences.get(Constants.USER_TYPE_KEY, 0)
    }

    private fun setUserType(type:Int){
        preferences.put(Constants.USER_TYPE_KEY,type)
    }

    override fun getUserAccessToken(): String {
        return preferences.get(Constants.USER_ACCESS_TOKEN_KEY, "")
    }

    /**
     * use this method to store the user access token
     *
     * @param token the token of the user
     */

    override fun setUserAccessToken(token: String) {
        preferences.put(Constants.USER_ACCESS_TOKEN_KEY, token)
    }


    override fun getUserProfileCompletedState() =
        preferences.get(Constants.USER_PROFILE_IS_COMPLETED_KEY, true)

    override fun setUserProfileCompletedState(completed: Boolean) {
        preferences.put(Constants.USER_PROFILE_IS_COMPLETED_KEY, completed)
    }

    override fun getUserProfileConfirmedState() =
        preferences.get(Constants.USER_PROFILE_IS_CONFIRMED_KEY, 1)

    override fun setUserProfileConfirmedState(confirmed: Int) {
        preferences.put(Constants.USER_PROFILE_IS_CONFIRMED_KEY, confirmed)
    }

    override fun setUserId(id: Int) {
        preferences.put(Constants.USER_ID_KEY,id)
    }

    override fun setTargetScreen(screen: Int) {
       preferences.put(Constants.TARGET_SCREEN,screen)
    }

    override fun getTargetScreen() =   preferences.get(Constants.TARGET_SCREEN, 1)



}