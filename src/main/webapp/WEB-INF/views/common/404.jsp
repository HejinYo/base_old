<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- Template Name: Clip-Two - Responsive Admin Template build with Twitter Bootstrap 3.x | Author: ClipTheme -->
<!--[if IE 8]><html class="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" lang="en"><![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- start: HEAD -->
<%-- Start: COMMON RESOURCES --%>
<%@ include file="/resources/js/common/commonresource.jsp" %>
<%-- End : COMMON RESOURCES --%>
<!-- end: HEAD -->
<!-- start: BODY -->
<body>
<!-- start: 404 -->
<div class="error-full-page">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 page-error">
                <div class="error-number text-azure">
                    404
                </div>
                <div class="error-details col-sm-6 col-sm-offset-3">
                    <h3>${message}</h3>
                    <p>
                        Unfortunately the page you were looking for could not be found.
                        <br>
                        It may be temporarily unavailable, moved or no longer exist.
                        <br>
                        Check the URL you entered for any mistakes and try again.
                        <br>
                        <a href="index.html" class="btn btn-red btn-return">
                            Return home
                        </a>
                        <br>
                        Still no luck?
                        <br>
                        Search for whatever is missing, or take a look around the rest of our site.
                    </p>
                    <form action="#">
                        <div class="input-group">
                            <input type="text" placeholder="keyword..." size="16" class="form-control">
                            <span class="input-group-btn">
										<button class="btn btn-azure">
											Search
										</button> </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<!-- end: BODY -->
</html>