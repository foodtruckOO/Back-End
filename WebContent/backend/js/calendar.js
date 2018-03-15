$(function() {
    // Easy pie charts
    var calendar = $('#calendar').fullCalendar({
	header: {
		left: 'prev,next',
		center: 'title',
		right: 'month,basicWeek,basicDay'
	},
	theme: true,
    selectable: true,
    selectHelper: true,
    select: function(start, end, allDay) {
        var title = prompt('Event Title:');
        if (title) {
            calendar.fullCalendar('renderEvent',
                {
                    title: title,
                    start: start,
                    end: end,
                    allDay: allDay
                },
                true // make the event "stick"
            );
        }
        calendar.fullCalendar('unselect');
    },
    droppable: true, // this allows things to be dropped onto the calendar !!!
    drop: function(date, allDay) { // this function is called when something is dropped
    
        // retrieve the dropped element's stored Event Object
        var originalEventObject = $(this).data('eventObject');
        
        // we need to copy it, so that multiple events don't have a reference to the same object
        var copiedEventObject = $.extend({}, originalEventObject);
        
        // assign it the date that was reported
        copiedEventObject.start = date;
        copiedEventObject.allDay = allDay;
        
        // render the event on the calendar
        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
        
        // is the "remove after drop" checkbox checked?
        if ($('#drop-remove').is(':checked')) {
            // if so, remove the element from the "Draggable Events" list
            $(this).remove();
        }
        
    },
	editable: true,
	// US Holidays
	events: request.getAttribute("data")
		/*[
	{
	title: 'All Day Event',
	start: '2018-02-01'
	},
	{
	title: 'Long Event',
	start: '2018-02-07',
	end: '2018-02-10'
	},
	{
	id: 999,
	title: 'Repeating Event',
	start: '2018-02-09T16:00:00'
	},
	{
	id: 999,
	title: 'Repeating Event',
	start: '2018-02-16T16:00:00'
	},
	{
	title: 'Conference',
	start: '2018-02-11',
	end: '2018-02-13'
	},
	{
	title: 'Meeting',
	start: '2018-02-12T10:30:00',
	end: '2018-02-12T12:30:00'
	},
	{
	title: 'Lunch',
	start: '2018-02-12T12:00:00'
	},
	{
	title: 'Meeting',
	start: '2018-02-12T14:30:00'
	},
	{
	title: 'Happy Hour',
	start: '2018-02-12T17:30:00'
	},
	{
	title: 'Dinner',
	start: '2018-02-12T20:00:00'
	},
	{
	title: 'Birthday Party',
	start: '2018-02-13T07:00:00'
	},
	{
	title: 'Click for Google',
	url: 'http://google.com/',
	start: '2018-02-28'
	}
	]*/
	
	});
});

$('#external-events div.external-event').each(function() {

    // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
    // it doesn't need to have a start or end
    var eventObject = {
        title: $.trim($(this).text()) // use the element's text as the event title
    };
    
    // store the Event Object in the DOM element so we can get to it later
    $(this).data('eventObject', eventObject);
    
    // make the event draggable using jQuery UI
    $(this).draggable({
        zIndex: 999999999,
        revert: true,      // will cause the event to go back to its
        revertDuration: 0  //  original position after the drag
    });
    
});