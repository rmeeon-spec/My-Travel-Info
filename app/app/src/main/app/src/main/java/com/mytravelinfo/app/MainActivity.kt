
package com.mytravelinfo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Forest = Color(0xFF174B3A)
private val Cream = Color(0xFFF7F5EF)
private val Gold = Color(0xFFC69B52)

data class Place(
    val name: String,
    val region: String,
    val description: String,
    val route: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TravelApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelApp() {
    var places by remember {
        mutableStateOf(
            listOf(
                Place("নীলগিরি", "বান্দরবান",
                    "মেঘে ঢাকা পাহাড় ও সবুজ প্রকৃতি।",
                    "বান্দরবান শহর থেকে স্থানীয় পরিবহনে যেতে হয়।"),
                Place("সাজেক ভ্যালি", "রাঙামাটি",
                    "পাহাড়, মেঘ ও সুন্দর সূর্যোদয়।",
                    "খাগড়াছড়ি হয়ে স্থানীয় জিপে যাওয়া যায়।"),
                Place("ইনানী সৈকত", "কক্সবাজার",
                    "পাথুরে সৈকত ও নীল সমুদ্র।",
                    "কক্সবাজার শহর থেকে মেরিন ড্রাইভ ধরে যেতে হয়।")
            )
        )
    }

    var search by remember { mutableStateOf("") }
    var addDialog by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf<Place?>(null) }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Forest,
            secondary = Gold,
            background = Cream
        )
    ) {
        Scaffold(
            containerColor = Cream,
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text("My Travel Info",
                                color = Forest,
                                fontWeight = FontWeight.Bold)
                            Text("Discover • Save • Explore",
                                fontSize = 11.sp,
                                color = Color.Gray)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Cream
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { addDialog = true },
                    containerColor = Forest,
                    contentColor = Color.White
                ) {
                    Text("+", fontSize = 28.sp)
                }
            }
        ) { padding ->

            if (selected != null) {
                Column(
                    Modifier.fillMaxSize()
                        .padding(padding)
                        .padding(18.dp)
                ) {
                    Text("← ফিরে যান",
                        color = Forest,
                        modifier = Modifier.clickable {
                            selected = null
                        })
                    Spacer(Modifier.height(20.dp))
                    Text(selected!!.name,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = Forest)
                    Text(selected!!.region, color = Gold)
                    Spacer(Modifier.height(20.dp))
                    Text("জায়গার বর্ণনা",
                        fontWeight = FontWeight.Bold)
                    Text(selected!!.description)
                    Spacer(Modifier.height(18.dp))
                    Text("কীভাবে যাবেন",
                        fontWeight = FontWeight.Bold)
                    Text(selected!!.route)
                }
            } else {
                LazyColumn(
                    Modifier.fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 16.dp)
                ) {
                    item {
                        Spacer(Modifier.height(12.dp))
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Forest
                            ),
                            shape = RoundedCornerShape(22.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(Modifier.padding(20.dp)) {
                                Text(
                                    "তোমার পরবর্তী গন্তব্য\nহতে পারে এখানেই!",
                                    color = Color.White,
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    "বাংলাদেশের সৌন্দর্য আবিষ্কার করো।",
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(Modifier.height(18.dp))
                        OutlinedTextField(
                            value = search,
                            onValueChange = { search = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("জায়গা বা জেলা খুঁজুন...") },
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp)
                        )
                        Spacer(Modifier.height(18.dp))
                        Text("জনপ্রিয় ভ্রমণস্থান",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = Forest)
                    }

                    items(
                        places.filter {
                            it.name.contains(search, true) ||
                            it.region.contains(search, true)
                        }
                    ) { place ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 7.dp)
                                .clickable { selected = place },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text("🏞️",
                                    fontSize = 35.sp)
                                Text(place.name,
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Forest)
                                Text(place.region, color = Gold)
                                Text(place.description)
                                Text("বিস্তারিত দেখুন →",
                                    color = Forest,
                                    fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    item { Spacer(Modifier.height(80.dp)) }
                }
            }

            if (addDialog) {
                AddPlaceDialog(
                    onDismiss = { addDialog = false },
                    onSave = {
                        places = listOf(it) + places
                        addDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun AddPlaceDialog(
    onDismiss: () -> Unit,
    onSave: (Place) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var region by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var route by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("নতুন জায়গা যোগ করুন") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(name, { name = it },
                    label = { Text("জায়গার নাম") })
                OutlinedTextField(region, { region = it },
                    label = { Text("জেলা / অঞ্চল") })
                OutlinedTextField(description, { description = it },
                    label = { Text("জায়গার বর্ণনা") })
                OutlinedTextField(route, { route = it },
                    label = { Text("কীভাবে যেতে হয়") })
            }
        },
        confirmButton = {
            TextButton(
                enabled = name.isNotBlank(),
                onClick = {
                    onSave(Place(name, region, description, route))
                }
            ) {
                Text("সংরক্ষণ")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("বাতিল")
            }
        }
    )
}
