package com.example.chatroom.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatroom.data.Room
import com.example.chatroom.viewmodel.RoomViewModel


@Composable
fun ChatRoomListScreen(
    roomViewModel: RoomViewModel = viewModel(),
    onJoinClicked: (Room) -> Unit
){

    val rooms by roomViewModel.rooms.observeAsState(emptyList())
    var showDialog by remember{ mutableStateOf(false) }
    var name by mutableStateOf("")

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),


    ) {
        Text("Chat Rooms", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn{
            items(rooms){room->
                RoomItem(room = room){
                    onJoinClicked(room)
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showDialog = true }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Create Room", color = Color.White, fontSize = 18.sp)
        }

        if (showDialog){
            AlertDialog( onDismissRequest = { showDialog = true },
                title = { Text("Create a new room") },
                text={
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }, confirmButton = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                if (name.isNotBlank()) {
                                    showDialog = false
                                    roomViewModel.createRoom(name)
                                }
                            }
                        ) {
                            Text("Add")
                        }
                        Button(
                            onClick = { showDialog = false }
                        ) {
                            Text("Cancel")
                        }
                    }
                })
        }
    }
}

@Composable
fun RoomItem(room: Room, onJoinClicked : (Room) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = room.name, fontSize = 16.sp, fontWeight = FontWeight.Normal)
        OutlinedButton(
            onClick = { onJoinClicked(room)  }
        ) {
            Text("Join")
        }
    }

}



@Preview(showBackground = true)
@Composable
fun ChatRoomListScreenPreview(){
    ChatRoomListScreen(){}
}



