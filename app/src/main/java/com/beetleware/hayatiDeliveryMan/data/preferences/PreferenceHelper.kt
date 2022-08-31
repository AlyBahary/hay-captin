package com.beetleware.hayatiDeliveryMan.data.preferences

import com.beetleware.hayatiDeliveryMan.data.network.model.AuthResponse


interface PreferenceHelper {

    /**
     * use this method to change user language
     *
     * @param language the language code (0 for en and 1 for arabic)
     */
    fun setUserLanguage(language: Int)

    /**
     * use this method to get the stored user language code or -1 if no language stored
     */
    fun getUserLanguage(): Int

    /**
     * GET Target Screen
     */
    fun setTargetScreen(screen: Int)
    fun getTargetScreen():Int

    /**
     * use this method to log out the user and remove his info
     */
    fun removeUser()

    /**
     * user logIn flag
     *
     * @return true if user is loggedIn, false otherwise
     */
    fun isUserLoggedIn(): Boolean

    /**
     * get the stored user id
     *
     * @return the user id
     */
    fun setUserId(id:Int)

    fun getUserId(): Int

    /**
     * get the stored user type
     */
    fun getUserType() :Int

    /**
     * get the stored user access token
     *
     * @return the user access token
     */
    fun getUserAccessToken(): String

    /**
     * get the user competing profile state
     *
     * @return 1 if user completed all required fields, 0 otherwise
     */
    fun getUserProfileCompletedState(): Boolean

    /**
     * use this method to store user completing required info state
     *
     * @param completed the completing state
     */
    fun setUserProfileCompletedState(completed: Boolean)


    /**
     * get the user confirmed profile state
     *
     * @return true if user confirmed his mobile num, 0 otherwise
     */
    fun getUserProfileConfirmedState(): Int

    /**
     * use this method to store user confirmed profile state
     *
     * @param confirmed the confirmed state
     */
    fun setUserProfileConfirmedState(confirmed: Int)


    fun storeUser(user: AuthResponse)

    fun setUserAccessToken(token:String)


}