package pablo.ruiz.handling

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

// Custom exception class
class DateOutOfRangeException(message: String) : Exception(message)

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val inputDate = "2025-07-01"

        try {
            // Parse the input date
            val selectedDate = LocalDate.parse(inputDate, dateFormatter)

            // Validate the date
            validateDate(selectedDate)
            Toast.makeText(this, "The selected date $selectedDate is within the allowed range", Toast.LENGTH_LONG).show()

        } catch (e: DateOutOfRangeException) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Invalid input. Please enter a valid date in the format yyyy-MM-dd", Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun validateDate(selectedDate: LocalDate) {
        val today = LocalDate.now()
        val maxAllowedDate = today.plus(6, ChronoUnit.MONTHS)

        if (selectedDate.isAfter(maxAllowedDate)) {
            throw DateOutOfRangeException("Selected date exceeds the 6-month limit")
        }
    }
}
