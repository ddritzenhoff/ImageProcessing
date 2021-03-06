<p align="center">
  <img src="https://github.com/ddritzenhoff/OOD-ImageProcessing/blob/master/proj1.gif?raw=true" alt="Short Project Demo"/>
</p>

Authors: Ben-oni Vainqueur and Dominik Ritzenhoff

Note: We purposely kept this to a minimum because our JavaDocs go into deeper detail.

  There are four main interfaces within the model package that define all of the classes: IPixel,
IPixelImage, ITransformation, and IModel.

  Beginning with the first referenced interface, IPixel
represents the most basic building block of an image. Pixel, the class which implements IPixel,
represents every color as some combination of RED, GREEN, and BLUE, which are each, in turn,
represented as an integer value between 0 and 255.

  With respect to IPixelImage, this interface enables the client to get copies of the PixelImage
and to render it into whatever format specified (e.g. PPM, PNG, etc). PixelImage, which
implements IPixelImage, contains a 2D collection of IPixels and is the way the ProcessingModel
(model) stores its images.

  With respect to ITransformation, this interface enables various transformations of an image. The
classes that implement this interface (Blur, Sharpen, Sepia, Greyscale,
AbstractColorTransformation, and AbstractFilterTransformation) act as function-objects to
change the passed-in images in their own unique ways. Something important to note, however, is
the use of AbstractFilterTransformation and AbstractColorTransformation. While the former abstracts
the functionality of any filter transformation (such as Blur and Sharpen), the latter abstracts
the functionality of any color transformation (Sepia and Greyscale).

  With respect to IModel, this interface makes any implementing model handle basic image
functions such as adding, removing, or transforming an image. In the same vein, ProcessingModel
implements IModel and does just this. The ProcessingModel operates on layers, which are a wrapper
class that encapsulates the data associated with collections IPixelImages, such as order,
visibility,and the correlated IPixelImage data.

  Finally, ImageUtil contains static functions that handle the transformation of various file
formats into IPixelImages. For now, it only contains the logic to translate a PPM image into an
IPixelImage.

References:

car: https://unsplash.com/photos/fwtXC2sP7Tg?utm_source=unsplash&utm_medium=referral&utm_content=creditShareLink
hot air balloon: https://unsplash.com/photos/hpTH5b6mo2s?utm_source=unsplash&utm_medium=referral&utm_content=creditShareLink

Licensing Agreement (we are allowed to use the images): https://unsplash.com/license

Edits:

- One of the changes that we made was creating a new model interface (ISwingModel), added a few
methods, and extended the IModel interface. We then added a class (SwingModel) which implemented
this interface. This class represents a GUI representation of a model using Swing and it contains
all of the previous capabilities of a ProcessingModel, plus additional functionality to streamline
its use with Swing.

- Another change we made is creating the GUI view. Obviously, this was done using Java Swing and
required an additional controller (SwingController) to handle its inputs and outputs. An interesting
thing to note is that this view used the emitter pattern, which made us create listeners and
handlers. This resulted in the addition of a few more interfaces and classes (IViewListener,
as an example).


