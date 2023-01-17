Image Processing Part 1

Table of Contents:
Section 1 - Design
    explains the design we chose as well as the purposes of every class and interface
Section 2 - Script
    a script of commands used to run our program that will load an image and display a few example 
    operations a user can perform, includes instructions of how to run this script in our program
Section 3 - A citation for the source of our image
Section 4 - A new section detailing what parts we changed/added
Section 5 - What parts of the program are complete

Section 1:
For our program we adhered to the model view controller design. We decided to format our MVC in a 
way that no parts, model, view, or controller directly depend on another. For the model, we created
an ImageModel interface to hold all the methods that models for images will need. For this iteration
of our program we only handle .ppm files, but we created an AbstractModel that implements the 
ImageModel interface so that if we needed to handle other file types, we could make a new model
class that extends the abstract model. So for now we have a PPMModel class that extends the 
aforementioned abstract. This PPMModel holds all the methods and implementations of the image 
processing application that were mentioned thus far in the assignment specifically applicable to 
the .ppm ASCII format. The method used currently for adding methods to the assignment we would be 
able to tack on additional functionality without having to compromise our current design. 
This would allow for our code to be extended rather than modified. By promoting extensibility we are
ensuring that we would no longer need to adjust our current implementation when preparing for new 
file types/ methods to write. Since all the methods that adjust the model are only contained in the 
model class, to implement a new model there would need to be no changes to the controller or view, 
only new implementation of parts being extended by literally extending the MVC in new classes. 
Changing topics to our pixel class, this class held all the information necessary for representing a
pixel. This class held not only the values red, green, and blue, but also the information such as
the luma value, intensity, and max value. So that if a later assignment prompted us to add new 
values to greyscale a pixel by we would simply add them to the class and then be able to pass it 
into our convertTo method. This method retrieves values from this class and then mutates the red, 
green, blue values into that new value specified by the client. For clarification, we decided to use
the primitive type long to represent these numerical values so that we would be able to represent
many more "channels" compared to a design that uses the primitive type int. We decided to use long
so that the user could be worry free about what bit representation they would decide to use.

For the controller, we used a variation of the Command Design Pattern in which all method calls send
the required information and requested method call to the class where the implementation is 
required. for example, when trying to vertically flip an image model, the controller will intake the
command and the desired model and call a method upon the model where the flip will occur. Doing this
removes the explicit need of the type of model (Type of file), as well as not handling any extra 
responsibility. The same thing occurs in the controller with the view. The controller uses the 
method renderMessage from the textView class in order to provide information to the user rather than
printing the information itself. The controller implements the ImageCMD interface that holds all the
commands that it will be asked to perform on the models it can handle. To extend its functionality, 
one would only need to create a new controller that extends the one we have created and having it 
implement an interface with any new methods they desire. This new controller would overide the 
proccessCommand() function and add new implementations and then refer back to the inherited method 
to use the old implementation. Due to the controller sending all calls to edit the model to the 
model, a new controller would not have to be made to implement new models or views. 

For the view of our current design we decided to create a textView class which extends an 
AbstractView class which implements the ImageView interface. We did this because we expect to create
an alternative view to our current one. The current TextView class handles appending textual 
information to an appendable which is then transmitted to the console and user by the controller.

Section 2:
Our Supported user instructions are:
load-image image-path image-name: Load an image from the specified path and refer it to henceforth 
in the program by the given image name.\
save-image image-path image-name: Save the image with the given name to the specified path which 
should include the name of the file.\
horizontal-flip image-name dest-image-name: Flip an image horizontally to create a new image, 
referred to henceforth by the given destination name.\
vertical-flip image-name dest-image-name: Flip an image vertically to create a new image, 
referred to henceforth by the given destination name.\ 
brighten increment image-name dest-image-name: brighten the image by the given increment to create
a new image, referred to henceforth by the given destination name. The increment may be positive
(brightening) or negative (darkening).\
darken increment image-name dest-image-name: brighten the image by the given increment to create a 
new image, referred to henceforth by the given destination name. The increment may be negative
(brightening) or positive (darkening).\
component-type image-name dest-image-name: Create a greyscale image with the component-type 
(Color representation to base greyscale off of) of the image with the given name, and refer to it 
henceforth in the program by the given destination name. These commands can be either red green, 
blue, value, luma, intensity components should be supported. For example a user can type 
red-component.\
blur image-name dest-image-name: Also called a Gaussian Blur, will blur the image by applying a 
filter to every channel of a pixel\
sepia-tone image-name dest-image-name: A characteristic reddish brown color which uses a color 
transformation supplied by a matrix.\
sharpen image-name dest-image-name: Sharpens the image by accentuating edges.\
grey-scale image-name dest-image-name: GreyScale of the image using the luma value.\
FILTER source-image mask-image dest-image: To apply a filter to part of an 
Image type the command followed by the image you want to edit, the mask, and the new images name.\
menu (Print supported instruction list)\
q or quit (quit the program)\

To run our program run the main method located in the test directory. The class is called MainImage.
An example script you could copy and paste into the console is as follows:
load-image res/Smaple1.ppm Smaple 
brighten 20 Smaple SmapleBright
vertical-flip Smaple SmapleFlipped
darken 40 Smaple SmapleDark
red-component SmapleBright SmapleBrightRedGreyScale
load-image res/SmapleBrightRedGreyScale SmapleBright
save-image res/SmapleBright.ppm SmapleBright

Section 3:
The images included in our resource directory have been masterfully created by us.
Specifically, Smaple1 and blur was drawn by Jarod Carlos DeSousa, and Exmaple and its copy 
ExmapleTest was created by Valluru Venkata Ratna Krishna Prasad Bablue Dheeraj Ram Valluru.
We authorize the use of these images for this program. Minio was created by Jarod DeSousa depicting 
a minion. Masterpiece was created by both Jarod and Dheeraj and is an abstract drawing.
We authorize the use for both of these images as well.:)

The bmp file was taken from a website online, the citation is below.
“Sample BMP File for Testing.” Learning Container,
2 Oct. 2020, https://www.learningcontainer.com/sample-bmp-file-for-testing/. 

Manhattan-small.png was taken straight from the canvas assignment page for Image-Processing Part 2.
This image was authorized for us to use by a TA. 

MinioMask.png was a mask created by Jarod on Gimp. He authorizes the use of the image for the 
assignment.

Section 4:
We changed the Map holding our models to hold a BetterImageModel.\
We fixed our model loading to load copies instead of references in the map.\
We changed the processing of commands in the controller to add functionality for new methods
blur, sharpen, grey scale, and sepia tone.\
We changed our save method to properly switch between models and save the specified image.\
We changed our getter which receives the model from the map to get a copy instead of a reference.\
Design pattern was not changed, only fixed bugs from previous methods and added functionality.
We introduced a GUI which tells the user what functions can be applied to an image and allows for 
the user to see histograms showing the user the amount of r g b values in an image as well as the
intensity value of an image. 
We also introduced a downsize method that downsizes the image to a certain width and height value.
We added a partial image manipulation method as well which allows for a user to insert a mask and
change only a part of an image.
In order to implement this partial image implementation we had to change how we handled user 
commands in our text based UI so that our controller could handle both normal filters and partial 
filters.
In order to implement the downsizing functionality we added the downsize method to the 
BetterImageModel interface so that all of our models could perform this method.

Section 5:
We were able to complete all functionality we were asked of during this assignment. We successfully
added all new functionality (sepia-tone, grey-scale, sharpen, and blur) and our program allows for 
users to save images from one file type to another as well. In short, we retain the support for the 
PPM file format and are able to switch between file types. Additionally, we allow for users to 
apply scripts in a txt file.