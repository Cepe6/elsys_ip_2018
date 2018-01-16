var filterData = {};
var currentData = {};

$(function () {
    var data = {};
    $.ajax({
        type: "GET",
        url: "/api/country",
        data: data,
        async: true,
        dataType: "json",
        success: function (data) {
            var appendString = "<tr>";


            $.each(data[0], function (key) {
                $("#searchFieldsSelect").append("<option class=\""+ key +"\" value=\"" + key + "\">" + key + "</option>");
                appendString += "<td style=\"border: 1px solid black; text-align: center;\"><h4>" + key + "</h4></td>";
            });

            appendString += "<td style=\"border: 1px solid black;\"></td><td style=\"border: 1px solid black;\"></td>"
            appendString += "</tr>";
            $("#resultTable").append(appendString);
            currentData = data;
            fillTable(data);
        }
    });
});

function download() {
    $.ajax({
        type: "GET",
        url: "/api/country/download",
        async: false
    });
}

function addSearchField() {
    var option = $('select[id="searchFieldsSelect"] option:selected');

    filterData[option.attr('value')] = [];
    $('#SearchFields').append("<div  id=\"SearchFields:" + option.attr('value') + "\">" + option.attr('value') + ":</br><input type='text' name='"+ option.attr('value') +"' onchange='inputFieldChange(this)' id='0'></div>");
    option.remove();
    var length = $("#searchFieldsSelect, option" ).length;
    if(length === 1) {
        $("#addButton").attr('disabled', 'disabled');
    }
}

function inputFieldChange(inputObject) {
    var fieldName = inputObject.name;
    filterData[fieldName][parseInt(inputObject.id)] = inputObject.value;

    getFiltered();
}

function getFiltered() {
    var query = "?";
    var fieldIndex = 0;
    $.each(filterData, function (key, value) {
        if(fieldIndex > 0) {
            query += "&";
        }
        query += key + "=";
        $.each(value, function (index, vValue) {
            if(index > 0)
                query += "&" + key + "=";
            query += vValue;
        });

        fieldIndex++;
    });

    var data = {};
    $.ajax({
        type: "GET",
        url: "/api/country" + query,
        data: data,
        async: true,
        dataType: "json",
        success: function (data) {
            currentData = data;
            fillTable(data);
        }
    });
}

function edit(button) {
    var id = parseInt($(button).closest("tr").children("td.id").text());
    $("#edit-form")[0].style.display = "inline-block";
    var fieldset = $("#edit-form").children("form").children("fieldset");
    fieldset.html("");

    var country = $(currentData).filter(function (index) {
        return currentData[index].id === id;
    });

    var isID = true;
    $.each(country[0], function(key, value) {
        if(isID) {
            isID = false;
        } else {
            fieldset.append("<label for='" + key + "'>" + key + "</label></br>");
            fieldset.append("<input type='text' name='" + key + "' value='" + value + "'></br></br>");
        }
    });

    fieldset.append("<button type='button' name='" + id + "' style='margin-right: 5px;' onclick='updateCountry(this)'>Submit</button>");
    fieldset.append("<button type='button' style='margin-left: 5px;' onclick='cancel()'>Cancel</button>");
}

function updateCountry(button) {
    var id = parseInt(button.name);
    var fields = $("#edit-form").children("form").children("fieldset").children("input");
    var country = {};
    country.id = id;
    $.each(fields, function(key, value) {
        country[value.name] = value.value;
    });

    $.ajax({
        type: "PUT",
        url: "/api/country/" + id,
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(country),
        success: function () {
            var data = {};
            $.ajax({
                type: "GET",
                url: "/api/country",
                async: true,
                data: data,
                success: function (data) {
                    currentData = data;
                    fillTable(data);
                }
            })
        }
    })

    $("#edit-form")[0].style.display = "none";
}

function cancel(){
    $("#edit-form")[0].style.display = "none";
}

function countryDelete(button) {
    var id = parseInt($(button).closest("tr").children("td.id").text());

    $.ajax({
        type: "DELETE",
        url: "/api/country/" + id,
        async: true,
        success: function () {
            var data = {};
            $.ajax({
                type: "GET",
                url: "/api/country",
                async: true,
                data: data,
                success: function (data) {
                    currentData = data;
                    fillTable(data);
                }
            })
        }
    });


}
var fillTable = function(data) {
    $('#resultTable').find("tr:gt(0)").remove();

    $.each(data, function (key, value) {
        var appendStr = "<tr>";
        $.each(value, function (vKey, vValue) {
            appendStr += "<td style=\"border: 1px solid black;\" class='" + vKey +"'>" + vValue + "</td>";
        });
        appendStr += "<td style=\"border: 1px solid black; text-align: center;\"><button type='button' onclick='edit(this)'>Edit</button></td>";
        appendStr += "<td style=\"border: 1px solid black; text-align: center;\"><button type='button' onclick='countryDelete(this)'>Delete</button></td>";
        appendStr += "</tr>";

        $("#resultTable").append(appendStr);
    });
};