package pablo.ruiz.handling.sectionB

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExceptionHandlingUI() {
    var exceptionMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Exception Handling", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Button to test positive number check
        Button(onClick = {
            try {
                checkPositiveNumber(-5)
            } catch (e: IllegalArgumentException) {
                exceptionMessage = e.message ?: "Unknown Error"
            }
        }, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text("Check Positive Number")
        }

        // Button to test date validation
        Button(onClick = {
            try {
                val selectedDate = LocalDate.of(2025, 7, 1)
                validateDate(selectedDate)
            } catch (e: DateOutOfRangeException) {
                exceptionMessage = e.message ?: "Unknown Error"
            }
        }, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text("Validate Date")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display exception message
        if (exceptionMessage.isNotEmpty()) {
            Text(
                text = "Exception: $exceptionMessage"
            )
        }
    }
}
