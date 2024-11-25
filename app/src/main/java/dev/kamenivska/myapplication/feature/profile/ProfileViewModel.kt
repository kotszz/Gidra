package dev.kamenivska.myapplication.feature.profile

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.viewModelScope
import dev.kamenivska.myapplication.domain.user.LogoutUseCase
import dev.kamenivska.myapplication.domain.user.RefreshUserNameUseCase
import dev.kamenivska.myapplication.domain.user.UserNameFlowUseCase
import dev.kamenivska.myapplication.main.BaseViewModel
import dev.kamenivska.myapplication.main.utils.loadBitmap
import dev.kamenivska.myapplication.main.utils.loadImageFromStorage
import dev.kamenivska.myapplication.main.utils.saveToInternalStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val context: Application,
    val userNameFlowUseCase: UserNameFlowUseCase,
    private val logoutUseCase: LogoutUseCase,
    refreshUserNameUseCase: RefreshUserNameUseCase,
): BaseViewModel() {

    private val imageUri = MutableSharedFlow<Uri>()
    private val bitmap = MutableSharedFlow<Bitmap>()
    private val _imageBitmap = MutableStateFlow<ImageBitmap?>(null)
    val imageBitmap = _imageBitmap.asStateFlow()

    init {
        refreshUserNameUseCase()

        viewModelScope.launch(Dispatchers.IO) {
            imageUri.collectLatest { uri ->
                uri.loadBitmap(context)?.let { bmp ->
                    bitmap.emit(bmp)
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            bitmap.collectLatest { bmp ->
                _imageBitmap.emit(bmp.asImageBitmap())
                bmp.saveToInternalStorage(context)
            }
        }
    }

    fun getProfilePicData() {
        viewModelScope.launch {
            loadImageFromStorage(context.getDir("imageDir", Context.MODE_PRIVATE).path)?.let {
                _imageBitmap.emit(
                    it.asImageBitmap()
                )
            }
        }
    }

    fun setImageUri(uri: Uri?) {
        viewModelScope.launch {
            uri?.let {
                imageUri.emit(uri)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _onLogout.emit(Unit)
        }
    }
}