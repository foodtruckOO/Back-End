<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>일정관리</title>
    
    <!-- jQuery UI -->
    <link href="https://code.jquery.com/ui/1.10.3/themes/redmond/jquery-ui.css" rel="stylesheet" media="screen">

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value='/backend/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:url value='/backend/vendor/metisMenu/metisMenu.min.css'/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value='/backend/dist/css/sb-admin-2.css'/>" rel="stylesheet">
    <link href="<c:url value='/backend/dist/css/calendar.css'/>" rel="stylesheet">
	
	<link href="<c:url value='/backend/vendor/fullcalendar/fullcalendar.css'/>" rel="stylesheet">
	
    <!-- Custom Fonts -->
    <link href="<c:url value='/backend/vendor/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <div id="wrapper">
        <!-- Navigation -->
        <jsp:include page="/backend/template/Top.jsp"/>
        <!-- Page Content -->
        <div id="page-wrapper">
        	<div class="row">
            	<div class="col-lg-12">
            	    <h1 class="page-header">이벤트 일정표</h1>
            	</div>
            	<!-- /.col-lg-12 -->
            </div>
            <div class="row">
               <div class="col-lg-12">
                   	<div class="panel panel-default">
                       <div class="panel-heading">
                           DataTables Advanced Tables
                   		</div>
                       <!-- /.panel-heading -->
						<div class="panel-body">
		  					<div id='calendar'></div>
                   		</div>
               		</div>
           		</div>
           	</div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="<c:url value='/backend/vendor/jquery/jquery.min.js'/>"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/backend/vendor/bootstrap/js/bootstrap.min.js'/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value='/backend/vendor/metisMenu/metisMenu.min.js'/>"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/backend/dist/js/sb-admin-2.js'/>"></script>
    <script src="<c:url value='/backend/vendor/fullcalendar/fullcalendar.js'/>"></script>
    <script src="<c:url value='/backend/vendor/fullcalendar/gcal.js'/>"></script>
    
    <!-- fullcalendar 데이터 소스. = src="<c:url value='/backend/js/calendar.js'/>"  -->
    <script>
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
        select: function(start){
        	location.href="<c:url value='/Back/EventWrite.do?s_date="+start+"'/>";
        },
        	/* function(start, end, allDay) {
            var title = prompt('Event-Title:');
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
        }, */
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
    	events: ${calendar}
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
    </script>
</body>

</html>
