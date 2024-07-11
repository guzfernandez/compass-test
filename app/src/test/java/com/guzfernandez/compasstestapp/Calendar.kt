package com.guzfernandez.compasstestapp

import org.junit.Test
import java.util.Date

// Create a new file within the test folder of your project, Calendar.kt. You can paste this entire comment into
// that file so you can easily refer to it throughout the exercise.
//
// Implement a class `Calendar` that is responsible for maintaining a
// collection of appointments. Each Appointment should contain:


data class Appointment(
    val name: String,
    val startTime: Date,
    val endTime: Date,
    val client: String
)

class Calendar {

    private var appointments: MutableList<Appointment> = mutableListOf()

    fun addNewAppointment(name: String, startTime: Date, endTime: Date, client: String) : Boolean {
        val appointment = Appointment(name, startTime, endTime, client)

        var canAdd = true

        //
        appointments.forEach {
            // If the start time is between range of time, can't add
            if(it.startTime < appointment.startTime && it.endTime > appointment.startTime) {
                canAdd = false
                return@forEach
            } else if(it.startTime < appointment.endTime && it.endTime > appointment.startTime) {
                canAdd = false
                return@forEach
            }

        }

        if (canAdd) {
            appointments.add(appointment)
        }
        return canAdd
    }

    fun getAppointmentsSize() : Int {
        return appointments.size
    }


}

class calendarTest {

    @Test
    fun `check appointments and add them to list`() = run {
        val listAppointments = listOf(
            Appointment("app1", Date(0), Date(100), "pepe"),
            Appointment("app2", Date(200), Date(300), "pepe"),
            Appointment("app3", Date(350), Date(450), "pepe"),
        )

        val cal = Calendar()

        listAppointments.forEach {
            val result = cal.addNewAppointment(it.name, it.startTime, it.endTime, it.client)
            assert(result)
        }

        //assert(cal.getAppointmentsSize() == 3)
    }

    @Test
    fun `add only available appointments to list`() = run {
        val listAppointments = listOf(
            Appointment("app1", Date(0), Date(100), "pepe"),
            Appointment("app2", Date(50), Date(150), "pepe"),
            Appointment("app3", Date(100), Date(200), "pepe"),
            Appointment("app3", Date(110), Date(190), "pepe"),
        )

        val cal = Calendar()

//        listAppointments.forEach {
//            cal.addNewAppointment(it.name, it.startTime, it.endTime, it.client)
//        }

        val added = cal.addNewAppointment(listAppointments[0].name, listAppointments[0].startTime, listAppointments[0].endTime, listAppointments[0].client)
        assert(added)

        val added2 = cal.addNewAppointment(listAppointments[1].name, listAppointments[1].startTime, listAppointments[1].endTime, listAppointments[1].client)
        assert(!added2)

        val added3 = cal.addNewAppointment(listAppointments[2].name, listAppointments[2].startTime, listAppointments[2].endTime, listAppointments[2].client)
        assert(added3)

        val added4 = cal.addNewAppointment(listAppointments[3].name, listAppointments[3].startTime, listAppointments[3].endTime, listAppointments[3].client)
        assert(!added4)

        //assert(cal.getAppointmentsSize() == 2)
    }

}

//
// - an appointment name
// - a start time
// - an end time
// - a client (the person with whom the agent will meet)
//
// The calendar should define two methods:
//
// 1. a method to add new appointments. Appointments may not overlap.
//    Examples:
//    - We try to add the appointments 10am-11am and 11am-12pm:
//      -> Both are added successfully
//    - We try to add the appointments 10am-11am and 10:30am-11:30pm
//      -> Only the first one is added successfully
// 2. a method to return a list of appointments between a given start and
// end time, sorted by start time
//    Example:
//      3 appointments have been added already:
//      - 10am-11am
//      - 11am-12pm
//      - 12pm-1pm
//      If the date range given is 10:30am to 12pm, the following appointment
//      should be returned:
//        - 10am-11am
//        - 11am-12pm
//
// You should validate your implementation by writing unit tests.
//
// Hint: you can easily create fake dates for your unit tests like this:
//
//   val date1 = Date(0)
//   val date2 = Date(100)