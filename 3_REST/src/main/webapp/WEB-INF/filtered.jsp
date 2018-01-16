<%--
  Created by IntelliJ IDEA.
  User: Cepe6
  Date: 1/10/2018
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Countries</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/scripts/search.js"></script>
</head>
<body>
    <h1>Countries</h1>
    <button type="button" onclick="download()">Download</button>
    <form action="upload" method="post" enctype="multipart/form-data">

        <p>Select a file : <input type="file" name="file" size="45" accept=".csv" /></p>
        <input type="submit" value="Upload CSV" />

    </form>
    <div>
        <input type="button" value="Add search field" id="addButton" onclick="addSearchField()">
        <select name="Search fields options" id="searchFieldsSelect"></select>
    </div>
    <div id="SearchFields"></div>
    <table id="resultTable" style="border: 1px solid black; width: 100%"></table>

    <div id="edit-form" title="Update user" style="text-align: center; display: none; background-color: white; position: absolute; width:20%; height: auto; top: 100px; margin-left: auto; margin-right: auto; left: 0; right: 0;">
        <form>
            <fieldset>
            </fieldset>
        </form>
    </div>
</body>
</html>
