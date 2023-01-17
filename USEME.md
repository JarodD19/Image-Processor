Instructions for typing commands:

load-image image-path image-name: Load an image from the specified path and refer it to henceforth 
in the program by the given image name.

load-image res/Exmaple.ppm Exmaple

save-image image-path image-name: Save the image with the given name to the specified path which 
should include the name of the file.

save-image res/Exmaple.ppm Exmaple

horizontal-flip image-name dest-image-name: Flip an image horizontally to create a new image, 
referred to henceforth by the given destination name.

horizontal-flip Exmaple ExmapleflipHoriz

vertical-flip image-name dest-image-name: Flip an image vertically to create a new image, referred 
to henceforth by the given destination name. 

vertical-flip Exmaple ExmapleVert

brighten increment image-name dest-image-name: brighten the image by the given increment to create 
a new image, referred to henceforth by the given destination name. The increment may be positive 
(brightening) or negative (darkening)

brighten 20 Exmaple ExmapleBright

darken increment image-name dest-image-name: brighten the image by the given increment to create a 
new image, referred to henceforth by the given destination name. The increment may be negative 
(brightening) or positive (darkening)

darken 20 Exmaple ExmapleDarken

component-type image-name dest-image-name: Create a greyscale image with the component-type 
(Color representation to base greyscale off of) of the image with the given name, and refer to 
it henceforth in the program by the given destination name. These commands can be either red 
green, blue, value, luma, intensity components should be supported. For example a user can type 
red-component.

red-component Exmaple ExmapleRed

green-component Exmaple ExmapleGreen

blue-component Exmaple ExmapleGreen

value-component Exmaple ExmapleValue

luma-component Exmaple ExmapleLuma

intensity-component Exmaple ExmapleIntensity

blur image-name dest-image-name: Also called a Gaussian Blur, will blur the image by applying a 
filter to every channel of a pixel

blur Exmaple Exmaple blur

sepia-tone image-name dest-image-name: A characteristic reddish brown color which uses a color 
transformation supplied by a matrix.

sepia-tone Exmaple ExmapleSep

sharpen image-name dest-image-name: Sharpens the image by accentuating edges.

sharpen Exmaple ExmapleSharp

grey-scale image-name dest-image-name: GreyScale of the image using the luma value.

grey-scale Exmaple ExmapleGrey

FILTER source-image mask-image dest-image: To apply a filter to part of an
Image type the command followed by the image you want to edit, the mask, and the new images name.

load-image res/minio.png min

load-image res/miniomask.png minmask

blur min miniomask minblurmask

menu (Print supported instruction list)

menu

q or quit (quit the program)

q

quit

User input ignores capitalization for commands.

Users will be able to save files loaded from one type of file into another type of file.
For example even though Exmaple is a ppm file a user would be able to save the image in png, bmp, or
even the jpg format.

Users can work on any amount of images they would like to load by specifying the name of the image
they would like to work on when executing a command.