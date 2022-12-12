# Version-Drive
drive similar to google drive

                                                                INTRODUCTION :

THE MAIN OBJECTIVE OF THIS PROJECT IS TO PROVIDE A PLATFORM TO THE USERS TO STORE THEIR FILES ON CLOUD . THEY CAN UPLOAD DOWNLOAD
, DELETE , UPDATE , PREVIEW, AND SHARE THE FILES WITH OTHER USERS ON THE SAME PLATFORM .
NOW ,A USER CAN SHARE HIS / HER FILES WITH OTHER USERS WITH SOME PERMISSION THAT MEANS THE SENDER HAS THE FULL RIGHTS TO PERMIT THE RECEIVER JUST TO VIEW UPDATE AND DELETE THE FILE .

                                                     PROPOSED SOLUTION / METHODOLOGY :


The methodology is totally based on developing websites , in which I used react js for the front end and spring boot for backend and local storage for storing the uploaded and shared
files and using mysql for the database. Now ,I used an iterative approach for developing my project
which gets improved after getting testing and re-evaluations. 
so the two important aspect of project is to focus on 4 major part that are :-

1-Security

2-drive working

3-sharing

4-Versioning and branching.

Let's start from the first point 

1-Security for that i use spring security framework for making project robust and secure with better authentication and authorization with Oauth verification and multiple layer verification process.
Spring security is well developed and highly respected by the IT industry and highly secure.

2-Second part is drive working that is mainly based on 4
functionality :-

1-upload.

2-delete.

3-update.

4-preview.

5-share.

For the backend i used spring boot with hibernate with mysql database.

3-Third part is Sharing files that should take place securely and precisely with no fault , for doing so we use spring security for security and while sharing file we provide 4 options that is how you want to share your file to receiver that is if you just want to allow user to preview the file or download or delete or upload a new version for the file.

4-4-Fourth part is Versioning and Branching. Version is done by storing updated files of the same parent file in a particular folder as new updated files are uploaded it will be stored as a new version.Branching is done to get better track how new updated versions of files are stored and who is uploading them and whom the files are shared.

                                               Requirement to run application for testing and use are :



Software requirement:

1-Web browser.

2-java ide.

3-VS code or any other text editor for frontend.

4-Mysql workbench.

Hardware requirements for making the application are at least 8gb ram , i5 or above processor. For
using the application you just need a browser and a device with internet connection to access the
internet and software to browse it.
Environment setup:

1-Java development toolkit in system for backend.

2-Node js setup in system for using react js.

3-Mysql setup for accessing and creating the database.

Fig2.2 describes how the workload of the implementation part is divided.

The major focus is on Security after that it is versioning and branching after that it's on working
of basic drive functionality and after that is a sharing mechanism. First when the application is
hosted and the user accesses it he/she makes an account after that he/she gets 15GB storage.
When he uploads a file it is stored in a form of folder, that is a folder of file name is created where
the file is shown and you can easily access the file from there.
Whenever the user uploaded its updated version it directly comes in that folder as a new version
with the date and time of upload.In this folder for each file a user can have some options available
that are to preview the file , delete the file ,update the file , share the file. Now if user want to share
the file he/she clicks on the share option now this is opens a modal on which you have to fill the
receiver’s email if the users exist it will give you permission to share and before sharing it ask you if
you want to just let receiver preview , delete , update , download the files. show if you choose
download that the user can download as well as preview the file, if you choose update then user
can preview and download file as well as with update the file and when you choose delete the
receiver has the right to delete , preview , download and update the files. Sharing a file doesn’t
store the copy of the file on the receiver’s drive but what it does is it send a reference of the
files to the receiver and when the receiver uploads the updated version of the file it will also store in
senders cloud storage and sender has full rights to delete the file.


                                                         Conclusion and Recommendation:
																														
																														
The results achieved at the end of the Project Design is that the user now can access his/her
files from the browser , he/she can preview ,update , delete , download and can share their
files with other users of the application.
Versioning helps users to handle their updated file having the parent file saved with it.
While sharing a file, the user can also provide the permission that sender gives to the
receiver while sharing the files.
Now this application makes it easy for the user to store their files and important data with
ease and also they can access their files from the browser.
Versioning helps users to handle their updated file having the parent file saved with it. In
Future ,chat rooms can be created in applications for users to interact with each other and
discuss files.Security can also be improved with Oauth and spring security
framework.Branching can be added in application to get better track on the sharing and
updation of files.

						      	FOR BETTER UNDERSTANDING VIEW THIS PDF ATTACHED BELOW:

[Project Report_MID TERM FORMAT.pdf](https://github.com/stormbreaker07/Version-Drive/files/10211365/Project.Report_MID.TERM.FORMAT.pdf)
