<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container border rounded border-primary p-0">
        <div class="row align-items-center">
            <div class="col-12">
                <table class="table table-primary table-striped table-bordered">
                    <tr>
                        <th>ID:</th>
                        <td>${myDto.id}</td>
                    </tr>
                    <tr>
                        <th>name:</th>
                        <td>${myDto.name}</td>
                    </tr>
                    <tr>
                        <th>Phone:</th>
                        <td>${myDto.phone}</td>
                    </tr>
                    <tr>
                        <th>E-Mail:</th>
                        <td>${myDto.email}</td>
                    </tr>
                    <tr>
                        <th>ZipCode:</th>
                        <td>${myDto.zipcode}</td>
                    </tr>
                    <tr>
                        <th>address1:</th>
                        <td>${myDto.address1}</td>
                    </tr>
                    <tr>
                        <th>adress2:</th>
                        <td>${myDto.address2}</td>
                    </tr>
                    <tr>
                        <th>Join Date:</th>
                        <td>${myDto.getFormedJoinDate()}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="row align-items-center">
            <div class="col-12 text-center">
                <a href="/index.jsp">
                    <button class="btn btn-primary">back</button>
                </a>
            </div>
        </div>
    </div>
</body>
</html>