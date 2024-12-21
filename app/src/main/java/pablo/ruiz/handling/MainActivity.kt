package pablo.ruiz.handling

import CommercialFormUI
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pablo.ruiz.handling.sectionB.ExceptionHandlingUI

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigator() {
    var selectedSection by remember { mutableStateOf("SectionA") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Select a Section", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = { selectedSection = "SectionA" }, modifier = Modifier.padding(8.dp)) {
                Text("Section A")
            }
            Button(onClick = { selectedSection = "SectionB" }, modifier = Modifier.padding(8.dp)) {
                Text("Section B")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        when (selectedSection) {
            "SectionA" -> CommercialFormUI()
            "SectionB" -> ExceptionHandlingUI()
        }
    }
}
