package com.nghicv.firebasechat.ui.login.information

import android.app.Application
import android.databinding.ObservableField
import com.nghicv.baseproject.BaseViewModel
import com.nghicv.firebasechat.data.model.Empty
import com.nghicv.firebasechat.data.model.User
import com.nghicv.firebasechat.data.repository.AppDataSource
import com.nghicv.firebasechat.data.repository.AppRepository
import io.reactivex.subjects.PublishSubject

class InputViewModel(private val appRepository: AppRepository, application: Application) :
        BaseViewModel(application) {

    val selectGenderSubject: PublishSubject<Empty> = PublishSubject.create()
    val updateDataSuccessSubject: PublishSubject<User> = PublishSubject.create()
    val updateDataErrorSubject: PublishSubject<String> = PublishSubject.create()

    val genderString = ObservableField<String>(Gender.MALE.value)
    var age = ObservableField<String>()
    val username = ObservableField<String>()

    var user: User? = null
        set(value) {
            field = value
            username.set(value?.name)
        }

    var gender = Gender.MALE
        set(value) {
            field = value
            genderString.set(value.value)
        }


    fun onSelectGender() {
        selectGenderSubject.onNext(Empty())
    }

    fun onSubmit() {
        if (!validateInput()) {
            updateDataErrorSubject.onNext("Please fill out all data field")
            return
        }

        user?.let {
            it.age = age.get()?.toInt()
            it.sex = genderString.get()
            appRepository.addUser(it, object : AppDataSource.OnDataChangedListener<User> {
                override fun onSuccess(data: User) {
                    updateDataSuccessSubject.onNext(data)
                }

                override fun onError(message: String) {
                    updateDataErrorSubject.onNext(message)
                }

            })
        }
    }

    private fun validateInput(): Boolean {
        return !age.get().isNullOrBlank() && !genderString.get().isNullOrBlank()

    }

    enum class Gender(val value: String) {
        MALE("Male"), FEMALE("Female")
    }
}