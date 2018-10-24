CS478, Project 2

Design and code an Android app that displays information about cars. The app’s main activity displays a
grid whose cells contain a thumbnail picture of a car (from outside the car) above the name of the car’s manufacturer.
For instance, a grid cell could contain a thumbnail showing a Mazda 3, above the name “Mazda
3”. Feel free to choose your images from pictures publicly-available (and not copyrighted or otherwise protected)
on the Internet. Also, feel free to choose how to fit the pictures into thumbnails appearing in your
grid view (e.g. whether to scale the picture or not, etc.) Make sure that thumbnails are separated by thin
spaces (no more than 5 dp) uniformly on all four sides when the grid is displayed on a Pixel 2 device. Also,
size the thumbnails appropriately, e.g., using 2 or 3 columns in portrait mode. You must include enough
thumbnails to fill at least most of a Pixel’s display with your picture grid (i.e., at least 6 cells if using two
columns).
Each grid cell supports two kinds of functionality, depending on whether an app viewer performs a
short vs. a long click on the cell. A short click brings up a new activity that shows the entire picture of
the selected car on the entire device display. This picture must be a higher resolution and bigger than the
thumbnail displayed earlier. The user can return to the grid view by selecting the “back” soft button on
the phone. If, instead, a user clicks anywhere on the displayed picture, you must open a browser activity
showing the web site of the car’s manufacturer. For instance, if your app shows a picture of a Mazda 3, the
app should bring up the Mazda 3’s web page from the official Mazda web site.
A long click on a grid cell brings up a “context menu” showing the following three options for the car
under consideration: (1) View the entire picture (similar to a short click); (2) Show the official web page of
the car manufacturer in a new activity; and (3) bring up a list view containing the names and addresses of at
least three car dealers for the selected manufacturer in greater Chicago area.
Implementation notes. For this project use a Pixel 2 device running Oreo (API level 27). Design
your gridview in such a way that it will display optimally in portrait mode, and reasonably well also in
landscape mode. You are not required to provide backward compatibility with previous Android versions or
compatibility with other Android devices. Finally, make sure to use your RAM carefully: Never hold more
than one of the larger pictures in RAM at the same time. (Penalties will apply if you do.)
You must work alone on this project. Submit the entire Studio project as a zip archive using the submission
link in the assignment’s page on Blackboard. This archive should contain the apk executable that you
generated when you tested your app on your Pixel 2 AVD. Also, make sure to include your first and last
names in the manifest file of your app, perhaps as an XML comment. No late submissions will be accepted.
