package pablo.ruiz.handling.sectionB
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
fun validateDate(selectedDate: LocalDate) {
    val today = LocalDate.now()
    val maxAllowedDate = today.plus(6, ChronoUnit.MONTHS)

    if (selectedDate.isAfter(maxAllowedDate)) {
        throw DateOutOfRangeException("Selected date exceeds the 6-month limit.")
    }
}
