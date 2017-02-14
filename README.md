A barebones Spring Boot with Hibernate app, which can easily be deployed to Heroku.

To deploy with Heroku CLI:

    $ heroku login
	$ heroku create [your-heroku-app-name]
    $ heroku git:remote -a [your-heroku-app-name]
    $ git push heroku master