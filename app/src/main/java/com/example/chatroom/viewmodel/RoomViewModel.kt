package com.example.chatroom.viewmodel

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroom.data.Result
import com.example.chatroom.data.Room
import com.example.chatroom.data.RoomRepository
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {
    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> get() = _rooms

    private val roomRepository: RoomRepository

    init {
        roomRepository = RoomRepository(Injection.instance())
        loadRooms()
    }

    // Create a new room and refresh the list of rooms
    fun createRoom(name: String) {
        viewModelScope.launch {
            // Call the repository to create a new room
            when (val result = roomRepository.createRoom(name)) {
                is Result.Success -> {
                    // After successfully creating a room, refresh the room list
                    loadRooms()
                }
                is Result.Error -> {
                    // Handle any errors if needed (optional)
                    // e.g., show a message to the user
                }
            }
        }
    }

    // Load rooms from the repository
    fun loadRooms() {
        viewModelScope.launch {
            when (val result = roomRepository.getRooms()) {
                is Result.Success -> {
                    _rooms.value = result.data
                }
                is Result.Error -> {
                    // Handle errors if needed (optional)
                }
            }
        }
    }
}
