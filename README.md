# Crowdsourcing

Crowdsourcing refers to a process that involves some kind of contribution from the general people or a specific group of people, whose belonging to the group and obligation for the contribution is open to them. The contribution is voluntary. Crowdsourcing Systems uses this notion to build open communities where people can contribute to achieve specific goals. Examples of such systems can be fundraising platforms such as Indigogo or nonprofit organizations raising funds with their websites.  There are crowdsourcing information systems such as Reddit enabling people to share information to one place.

One important area where crowdsourcing can be useful is helping people do their projects by enabling collaboration among them. MTL Works is such a crowdsourcing system aimed to facilitate remote group projects. This system is developed by trying to understand anthropomorphic aspects of users.

Major Technical Decisions

Architectural Spike
The architecture of MTL Works Crowdsourcing System is a two-tier style involving interactions between Java Servlets and JSP pages in a project repository in Tomcat 8 application server running from Eclipse IDE. The database server is MySQL running on 3306 port as a Windows process initiated from XAMPP. Additionally the MySQL database is managed from MySQL Workbench database manager for Windows. The MySQL connection is ported to the Eclipse Java project using JConnector plugin (available from MySQL official website) residing in the project’s WEB-INF folder. The JSP pages are also loaded with JSTL to enable better scripting capabilities. Only the JSP pages are displayed on the browser using Tomcat’s 8080 port.

We can think of the system also as a layer view where the browser resides on top of the layer, the application server in the middle and the database server at the bottom. Some other architectural styles identified in the system are Client-Server pattern and Pipe-filter pattern.
 

Software Development Process
The choice of software development process depends on available time, size of development team and work hours for each member of the team. Given the timeline to complete the project and two members for the team, Agile methodologies are suitable. For this particular project we decided to follow specific variants of agile methodologies , namely Extreme Programming (Architectural Spike preceded by tested feature release with review of each sprints), Pair Programming (helps to maximize productivity of team members), and basic agile principle to divide whole project timeline in short iterations, usually 5 days, setting goals for the iteration and have a weekly retrospective meeting to track progress. We also set strategy to release features upon testing them one by one, combining principles of Feature Driven and Test Driven development.

Languages and Tools
Eclipse Mars is used as the primary development tool IDE with Java EE perspective. Eclipse IDE is connected to the MySQL server, running with XAMPP via JConnector plugin, and the MySQL server is in turn connected to MYSQL Workbench program for Windows. The source code is setup with Git version control system and connected to a repository in Github [1]. We decided to use Twitter Bootstrap so that we could easily design the site by using various built in CSS and JavaScript components. A number of JQuery plugins for registration and new project form validations. AJAX is used to restrict an email address to be used for only one account, although using the same address, a person can register separately as a client and also a freelancer.
