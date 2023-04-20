<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    </head>

    <body>
        <form action="/upload.file" method="post" enctype="multipart/form-data">
            <table border="1" align="center">
                <tr>
                    <th>File upload</th>
                </tr>
                <tr>
                    <td align="center"><input type="text" name="message" placeholder="전송할 메세지 입력"></td>
                </tr>
                <tr>
                    <td align="center"><input type="file" name="file"></td>
                </tr>
                <tr>
                    <td align="center">
                        <button>Upload</button>
                        <a href="/"><button type="button">Back</button></a>
                    </td>
                </tr>
            </table>
        </form>
    </body>

    </html>