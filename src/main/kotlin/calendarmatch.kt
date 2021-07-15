
fun calendarMatching(calendar1: List<List<String>>, dailyBounds1 : List<String>, calendar2 : List<List<String>>,
                     dailyBounds2 : List<String>, meetingDuration : Int) : List<List<String>>{

    val updatedCalendar1 = updateCalendar(calendar1, dailyBounds1)
    /*print("Calendar1 : " + calendar1 +"\n")
    print("Updated Calendar1 : " + updatedCalendar1 +"\n")*/
    val updatedCalendar2 = updateCalendar(calendar2, dailyBounds2)
   /* print("Calendar2 : " + calendar2 +"\n")
    print("Updated Calendar2 : " + updatedCalendar2 +"\n")*/
    val mergedCalendar = mergeTwoUpdatedCalendars(updatedCalendar1, updatedCalendar2)
    //print("Merged Calendar: " + mergedCalendar +"\n")
    val flattenedCalendar = flattenedCalendar(mergedCalendar)
    //print("Flattened Calendar: " + flattenedCalendar +"\n")

    return getMatchingAvailabilities(flattenedCalendar, meetingDuration)
}

fun updateCalendar(calendar: List<List<String>>, dailyBounds : List<String>) : List<List<Int>>{

    val startDayTimeToStartDailyBounds = listOf(listOf("0:00", dailyBounds[0]))
    val endDailyBoundsToEndDayTime = listOf(listOf(dailyBounds[1], "23:59"))
    val updatedCalendar = startDayTimeToStartDailyBounds + calendar + endDailyBoundsToEndDayTime

    // Collection (List,hashtable) transformation
    return updatedCalendar.map {
        meeting -> meeting.map { time -> timeToMinutes(time) }
    }

}

fun mergeTwoUpdatedCalendars(calendar1: List<List<Int>>, calendar2: List<List<Int>>) : List<List<Int>>{

    val mergedMeetings = mutableListOf<List<Int>>()
    var i=0
    var j=0

    while (i< calendar1.size && j <calendar2.size){

        val meeting_1 = calendar1[i]
        val meeting_2 = calendar2[j]
        if(meeting_1[0] < meeting_2[0]){ // check the meeting start time
            mergedMeetings.add(meeting_1)
            i++
        }else{
            mergedMeetings.add(meeting_2)
            j++
        }
    }

    while (i< calendar1.size) {
        mergedMeetings.add(calendar1[i++])
    }

    while (j <calendar2.size){
        mergedMeetings.add(calendar2[j++])
    }

  return mergedMeetings
}

fun flattenedCalendar(calendar : List<List<Int>>) : List<List<Int>>{

    val flattened = mutableListOf(listOf(calendar[0][0], calendar[0][1]))
    for (i in 1 until calendar.size){
        val currentMeeting = calendar[i]
        val previousMeeting = flattened[flattened.size - 1]

        val(currentStart, currentEnd) = currentMeeting
        val(previousStart, previousEnd) = previousMeeting

        if(previousEnd >= currentStart){
            val newPreviousMeeting = listOf(previousStart, maxOf(previousEnd, currentEnd))
            flattened[flattened.size - 1] = newPreviousMeeting
        }
        else{
            flattened.add(listOf(currentStart, currentEnd))
        }

    }

 return flattened

}

fun getMatchingAvailabilities(calendar : List<List<Int>>, meetingDuration: Int) : List<List<String>>{

    val matchingAvailabilities = mutableListOf<List<Int>>()
    for (i in 1 until calendar.size){
        val start = calendar[i-1][1]
        val end = calendar[i][0]
        val availabilityDuration = end - start
        if (availabilityDuration >= meetingDuration){
            matchingAvailabilities.add(listOf(start, end))
        }
    }

    return matchingAvailabilities.map {
        meeting -> meeting.map {
            time -> minuteToTime(time)
        }
    }
}

fun timeToMinutes(time: String) : Int{
    val(hours, minutes) = time.split(":").map { str -> str.toInt() }
    return hours * 60 + minutes
}

fun minuteToTime(minute : Int) : String{
    val hours = minute/60
    val mins = minute% 60
    val hoursToString = hours.toString()
    val minsToString = if (mins < 10) "0"+mins.toString() else mins.toString()

    return hoursToString+ ":" + minsToString
}


fun main(args: Array<String>){

    val calendar1 = listOf(listOf("9:00", "10:30"), listOf("12:00", "13:00"), listOf("16:00", "18:00"))
    val dailyBounds1 = listOf("9:00", "20:00")
    val calendar2 = listOf(listOf("10:00", "11:30"), listOf("12:30", "14:30"), listOf("14:30", "15:00"), listOf("16:00", "17:00"))
    val dailyBounds2 = listOf("10:00", "18:30")
    val meetingDuration = 30

    print(calendarMatching(calendar1, dailyBounds1, calendar2, dailyBounds2, meetingDuration))
}