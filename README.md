# movie.catalog
This is Java/Spring Framework project for my educational purposes.

Stack: 
Java, HTML/CSS.

Frameworks and tools: 
Maven, Spring, PostgreSQL, Spring Security, Hibernate, Lombok, Freemarker Template Engine, JPA/JDBC.

Description:
The project itself is a movie catalog that uses IMDb API to collect information about different titles. 
Features:
- add any title by IMDb identificator
- the main catalog page and separate page for every item with more info about it
- Spring Security for registration/login, password encoding. (checking unique email, etc)
- Email confirmation using Google account, profile verification
- admin and moderator roles. The first can add/delete titles from catalog. The last can moderate user reviews to every title.
- Every user can add/delete some review/comment for movie. At first it is visible only for moderator, who can approve or delete the comment, in addition he can block user account or restrict user from writing reviews at all.
- User has his favourite list, where he can add titles from catalog

In future I want to make a RESTfull application to learn how to work with React.
