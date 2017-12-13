import java.util.Arrays;

/**
 * A class that runs implements several simple transformations on 2D image arrays.
 * <p>
 * This file contains stub code for your image transformation methods that are called by the main
 * class. You will do your work for this MP in this file.
 * <p>
 * Note that you can make several assumptions about the images passed to your functions, both by the
 * web front end and by our testing harness:
 * <ul>
 * <li>You will not be passed empty images.</li>
 * <li>All images will have even width and height.</li>
 * </ul>
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/4/">MP4 Documentation</a>
 */
public class Transform {

    /**
     * Default amount to shift an image's position. Not used by the testing suite, so feel free to
     * change this value.
     */
    public static final int DEFAULT_POSITION_SHIFT = 16;
    /**
     * Pixel value to use as filler when you don't have any valid data. All white with complete
     * transparency. DO NOT CHANGE THIS VALUE: the testing suite relies on it.
     */
    public static final int FILL_VALUE = 0x00FFFFFF;
    /**
     * COLOR VALUE HOLDER.
     */
    public static final int C1_VALUE = 0x000000FF;
    /**
     * COLOR VALUE HOLDER.
     */
    public static final int C2_VALUE = 0xFFFFFF00;
    /**
     * COLOR VALUE HOLDER.
     */
    public static final int C3_VALUE = 0x0000FF00;
    /**
     * COLOR VALUE HOLDER.
     */
    public static final int C4_VALUE = 0xFFFF00FF;
    /**
     * COLOR VALUE HOLDER.
     */
    public static final int C5_VALUE = 0x00FF0000;
    /**
     * COLOR VALUE HOLDER.
     */
    public static final int C6_VALUE = 0xFF00FFFF;
    /**
     * alpha value holder.
     */
    public static final int ALPHA_SHIFT = 0xFF000000;
    /**
     * all possible.
     */
    public static final int ALL_POSSIBLE = 255;
    /**
     * shift holder for alpha changes.
     */
    public static final int TO_AND_BY = 0XFF;
    /**
     * THE INTEGER 8.
     */
    public static final int EIGHT = 8;
    /**
     * THE INTEGER 16.
     */
    public static final int SIXTEEN = 16;
    /**
     * THE INTEGER 24.
     */
    public static final int TFOUR = 24;

    /**
     * Shift the image up by the specified amount.

     * @param originalImage the image to shift up
     * @param amount the amount to shift the image up
     * @return the shifted image
     */
    public static int[][] shiftUp(final int[][] originalImage, final int amount) {
         int[][] shiftedImage = new int[originalImage.length][originalImage[0].length];
         for (int row = 0; row < originalImage.length; row++) {
             for (int col = 0; col < originalImage[0].length; col++) {
                 if (col >= originalImage[0].length - amount) {
                     shiftedImage[row][col] = FILL_VALUE;
                 } else {
                     shiftedImage[row][col] = originalImage[row][col + amount];
                 }
             }
         }
         return shiftedImage;
     }


    /**
     * Shift down like above.
     *
     * @param originalImage the image to shift to the down
     * @param amount the amount to shift the image down
     * @return the shifted image
     */
    public static int[][] shiftDown(final int[][] originalImage, final int amount) {
        int[][] shiftedImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                if (col < amount) {
                    shiftedImage[row][col] = FILL_VALUE;
                } else {
                    shiftedImage[row][col] = originalImage[row][col - amount];
                }
            }
        }
        return shiftedImage;
    }

    /**
     * Shift left above.
     *
     * @param originalImage the image to shift to the left
     * @param amount the amount to shift the image to the left
     * @return the shifted image
     */
    public static int[][] shiftLeft(final int[][] originalImage, final int amount) {
        int[][] shiftedImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                if (row >= originalImage.length - amount) {
                    shiftedImage[row][col] = FILL_VALUE;
                } else {
                    shiftedImage[row][col] = originalImage[row + amount][col];
                }
            }
        }
        return shiftedImage;
    }

    /**
     * Shift Right like above.
     *
     * @param originalImage the image to shift to the Right
     * @param amount the amount to shift the image to the Right
     * @return the shifted image
     */
    public static int[][] shiftRight(final int[][] originalImage, final int amount) {
         int[][] shiftedImage = new int[originalImage.length][originalImage[0].length];
         for (int row = 0; row < originalImage.length; row++) {
             for (int col = 0; col < originalImage[0].length; col++) {
                 if (row < amount) {
                     shiftedImage[row][col] = FILL_VALUE;
                 } else {
                     shiftedImage[row][col] = originalImage[row - amount][col];
                 }
             }
         }
         return shiftedImage;
     }

    /**
     * Rotate the image right by 90 degrees around its center.
     * <p>
     * Any pixels rotated in from off screen should be filled with FILL_VALUE. This function <i>does
     * not modify the original image</i>.
     *
     * @param originalImage the image to rotate right 90 degrees
     * @return the rotated image
     */
    public static int[][] rotateRight(final int[][] originalImage) {
        int[][] rotatedImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                rotatedImage[row][col] = FILL_VALUE;
            }
        }
        float rowHalf = (float) originalImage.length / 2;
        float colHalf = (float) originalImage[0].length / 2;
        if (rowHalf < colHalf) {
            for (int row = 0; row < originalImage.length; row++) {
                for (int col = Math.round(colHalf - rowHalf);
                        col < Math.round(colHalf + rowHalf); col++) {
                    rotatedImage[row][col] =
                            originalImage[col - Math.round(colHalf - rowHalf)]
                                    [Math.round(colHalf + rowHalf) - 1 - row];
                }
            }
        } else {
            for (int row = Math.round(rowHalf - colHalf);
                    row < Math.round(rowHalf + colHalf); row++) {
                for (int col = 0; col < originalImage[0].length; col++) {
                    rotatedImage[row][col] =
                            originalImage[col + Math.round(rowHalf - colHalf)]
                                    [Math.round(rowHalf + colHalf) - 1 - row];
                }
            }
        }
        return rotatedImage;
    }

    /**
     * Rotate the image left like above.
     *
     * @param originalImage the image to rotate
     * @return rotateRight image rotated to left
     */
    public static int[][] rotateLeft(final int[][] originalImage) {
        int[][] rotatedImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                rotatedImage[row][col] = FILL_VALUE;
            }
        }
        float rowHalf = (float) originalImage.length / 2;
        float colHalf = (float) originalImage[0].length / 2;
        if (rowHalf < colHalf) {
            for (int row = 0; row < originalImage.length; row++) {
                for (int col = Math.round(colHalf - rowHalf);
                        col < Math.round(colHalf + rowHalf); col++) {
                    rotatedImage[row][col] =
                            originalImage[Math.round(colHalf + rowHalf) - col - 1]
                                    [row + Math.round(colHalf - rowHalf)];
                }
            }
        } else {
            for (int row = Math.round(rowHalf - colHalf);
                    row < Math.round(rowHalf + colHalf); row++) {
                for (int col = 0; col < originalImage[0].length; col++) {
                    rotatedImage[row][col] =
                            originalImage[Math.round(rowHalf + colHalf) - col - 1]
                                    [row - Math.round(rowHalf - colHalf)];
                }
            }
        }
        return rotatedImage;
    }

    /**
     * Flip horizontally.
     *
     * @param originalImage the image to rotate
     * @return flipHorizontal flip image
     */
    public static int[][] flipHorizontal(final int[][] originalImage) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                outputImage[originalImage.length - 1 - row][col] = originalImage[row][col];
            }
        }
        return outputImage;
    }

    /**
     * Flip vertically.
     * note to self remember to fix param tags later !!! DON't FORGET !!
     * @param originalImage the image to flip
     * @return flipHorizantal flip image
     */
    public static int[][] flipVertical(final int[][] originalImage) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                outputImage[row][originalImage[0].length - 1 - col] = originalImage[row][col];
            }
        }
        return outputImage;
    }

    /**
     * Default amount to shift colors by. Not used by the testing suite, so feel free to change this
     * value.
     */
    public static final int DEFAULT_COLOR_SHIFT = 32;

    /**
     * Add red to the image.
     * RGBA hex value stores the values as 0xAABBGGRR.
     * <p>
     * This function <i>does not modify the original image</i>. It should also not generate any new
     * filled pixels.
     *
     * @param originalImage the image to add red to
     * @param amount the amount of red to add
     * @return the recolored image
     */
    public static int[][] moreRed(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                int finalPixel = originalImage[row][col];
                int pixel = finalPixel & C1_VALUE;
                if ((pixel + amount) > ALL_POSSIBLE) {
                    pixel = ALL_POSSIBLE;
                } else {
                    pixel += amount;
                }
                outputImage[row][col] = (finalPixel & C2_VALUE) | (pixel & C1_VALUE);
            }
        }
        return outputImage;
    }
    /**
     * Subtract red from the image.
     *
     * @param originalImage the image to decrease red to
     * @param amount the amount of red to decrease
     * @return the recolored image
     */
    public static int[][] lessRed(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                int finalPixel = originalImage[row][col];
                int pixel = finalPixel & C1_VALUE;
                if (pixel < amount) {
                    pixel = 0;
                } else {
                    pixel -= amount;
                }
                outputImage[row][col] = (finalPixel & C2_VALUE) | (pixel & C1_VALUE);
            }
        }
        return outputImage;
    }

    /**
     * Add green to the image.
     *
     * @param originalImage the image to add green
     * @param amount amount to decrease green by
     * @return the recolored image
     */
    public static int[][] moreGreen(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                int finalPixel = originalImage[row][col];
                int pixel = (finalPixel & C3_VALUE) >> EIGHT;
                if ((pixel + amount) > ALL_POSSIBLE) {
                    pixel = ALL_POSSIBLE;
                } else {
                    pixel += amount;
                }
                outputImage[row][col]  = (finalPixel & C4_VALUE) | ((pixel & C1_VALUE) << EIGHT);
            }
        }
        return outputImage;
    }

    /**
     * Subtract green from the image.
     *
     * @param originalImage the image to decrease green
     * @param amount to decrease green by
     * @return the recolored image
     */
    public static int[][] lessGreen(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                int finalPixel = originalImage[row][col];
                int pixel = (finalPixel & C3_VALUE) >> EIGHT;
                if (pixel < amount) {
                    pixel = 0;
                } else {
                    pixel -= amount;
                }
                outputImage[row][col] = (finalPixel & C4_VALUE) | ((pixel & C1_VALUE) << EIGHT);
            }
        }
        return outputImage;
    }

    /**
     * Add blue to the image.
     *
     * @param originalImage the image to add blue
     * @param amount to add blue by
     * @return the recolored image
     */
    public static int[][] moreBlue(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                int finalPixel = originalImage[row][col];
                int pixel = (finalPixel & C5_VALUE) >> SIXTEEN;
                if ((pixel + amount) > ALL_POSSIBLE) {
                    pixel = ALL_POSSIBLE;
                } else {
                    pixel += amount;
                }
                outputImage[row][col] = (finalPixel & C6_VALUE) | ((pixel & C1_VALUE) << SIXTEEN);
            }
        }
        return outputImage;
    }

    /**
     * Decrease blue from the image.
     *
     * @param originalImage the image to decrease blue
     * @param amount to decrease blue by
     * @return the recolored image
     */
    public static int[][] lessBlue(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                int finalPixel = originalImage[row][col];
                int pixel = (finalPixel & C5_VALUE) >> SIXTEEN;
                if (pixel < amount) {
                    pixel = 0;
                } else {
                    pixel -= amount;
                }
                outputImage[row][col] = (finalPixel & C6_VALUE) | ((pixel & C1_VALUE) << SIXTEEN);
            }
        }
        return outputImage;
    }


    /**
     * Add alpha to the image.
     *
     * @param originalImage the image to increase alpha on
     * @param amount to increase alpha by
     * @return moreAlpha increased alpha image
     */
    public static int[][] moreAlpha(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                int finalPixel = originalImage[row][col];
                int pixel = (finalPixel >> TFOUR) & TO_AND_BY;
                if ((pixel + amount) > ALL_POSSIBLE) {
                    pixel = ALL_POSSIBLE;
                } else {
                    pixel += amount;
                }
                outputImage[row][col] = (finalPixel & FILL_VALUE) | ((pixel & C1_VALUE) << TFOUR);
            }
        }
        return outputImage;
    }


    /**
     * Add alpha to the image.
     *
     * @param originalImage the image to decrease alpha on
     * @param amount to decrease alpha by
     * @return lessAlpha decreased alpha image
     */
    public static int[][] lessAlpha(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                int finalPixel = originalImage[row][col];
                int pixel = (finalPixel >> TFOUR) & TO_AND_BY;
                if (pixel < amount) {
                    pixel = 0;
                } else {
                    pixel -= amount;
                }
                outputImage[row][col] = (finalPixel & FILL_VALUE) | ((pixel & C1_VALUE) << TFOUR);
            }
        }
        return outputImage;
    }

    /**
     * The default resize factor. Not used by the testing suite, so feel free to change this value.
     */
    public static final int DEFAULT_RESIZE_AMOUNT = 2;

    /**
     * Shrink in the vertical axis around the image center.
     * <p>
     * An amount of 2 will result in an image that is half its original height. An amount of 3 will
     * result in an image that's a third of its original height. Any pixels shrunk in from off
     * screen should be filled with FILL_VALUE. This function <i>does not modify the original
     * image</i>.
     *
     * @param originalImage the image to shrink
     * @param amount the factor to shrink by
     * @return the shrunken image
     */
    public static int[][] shrinkHorizontal(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                outputImage[row][col] = FILL_VALUE;
            }
        }
        int rowHalf = originalImage.length / 2;
        int newRow = 1;
        for (int row = rowHalf - amount; row >= 0; row -= amount) {
            for (int col = 0; col < originalImage[0].length; col++) {
                outputImage[rowHalf - newRow][col] = originalImage[row][col];
            }
            newRow++;
        }
        newRow = 0;
        for (int row = rowHalf; row < originalImage.length; row += amount) {
            for (int col = 0; col < originalImage[0].length; col++) {
                outputImage[rowHalf + newRow][col] = originalImage[row][col];
            }
            newRow++;
        }
        return outputImage;
    }

    /**
     * Expand image.
     *
     * @param originalImage the image to expand
     * @param amount to expand by
     * @return expandHorizontal expanded image
     */
    public static int[][] expandHorizontal(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        int rowHalf = originalImage.length / 2;
        int newRow = 0;
        for (int row = rowHalf - 1; row >= 0; row--) {
            for (int col = 0; col < originalImage[0].length; col++) {
                outputImage[row][col] = originalImage[rowHalf - 1 - newRow / amount][col];
            }
            newRow++;
        }
        newRow = 0;
        for (int row = rowHalf; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                outputImage[row][col] = originalImage[rowHalf + newRow / amount][col];
            }
            newRow++;
        }
        return outputImage;
    }
    /**
     * Shrink image.
     *
     * @param originalImage the image to shrink
     * @param amount to shrink by
     * @return shrinkVertical shrunk image
     */
    public static int[][] shrinkVertical(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                outputImage[row][col] = FILL_VALUE;
            }
        }
        int colHalf = originalImage[0].length / 2;
        for (int row = 0; row < originalImage.length; row++) {
            int newCol = 1;
            for (int col = colHalf - amount; col >= 0; col -= amount) {
                outputImage[row][colHalf - newCol] = originalImage[row][col];
                newCol++;
            }
        }
        for (int row = 0; row < originalImage.length; row++) {
            int newCol = 0;
            for (int col = colHalf; col < originalImage[0].length; col += amount) {
                outputImage[row][colHalf + newCol] = originalImage[row][col];
                newCol++;
            }
        }
        return outputImage;
    }
    /**
     * Expand image.
     *
     * @param originalImage the image to expand
     * @param amount to expand by
     * @return expandVertical expanded image
     */
    public static int[][] expandVertical(final int[][] originalImage, final int amount) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        int colHalf = originalImage[0].length / 2;
        for (int row = 0; row < originalImage.length; row++) {
            int newCol = 0;
            for (int col = colHalf - 1; col >= 0; col--) {
                outputImage[row][col] = originalImage[row][colHalf - 1 - newCol / amount];
                newCol++;
            }
        }
        for (int row = 0; row < originalImage.length; row++) {
            int newCol = 0;
            for (int col = colHalf; col < originalImage[0].length; col++) {
                outputImage[row][col] = originalImage[row][colHalf + newCol / amount];
                newCol++;
            }
        }
        return outputImage;
    }

    /**
     * Remove a green screen mask from an image.
     * <p>
     * This function should remove primarily green pixels from an image and replace them with
     * transparent pixels (FILL_VALUE), allowing you to achieve a green screen effect. Obviously
     * this function will destroy pixels, but it <i>does not modify the original image</i>.
     * <p>
     * While this function is tested by the test suite, only extreme edge cases are used. Getting it
     * work work will with real green screen images is left as a challenge for you.
     *
     * @param originalImage the image to remove green from
     * @return the image with the green removed
     */
    public static int[][] greenScreen(final int[][] originalImage) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                int finalPixel = originalImage[row][col];
                int redComponent = finalPixel & C1_VALUE;
                int greenComponent = (finalPixel & C3_VALUE) >> EIGHT;
                int blueComponent = (finalPixel & C5_VALUE) >> SIXTEEN;
                if ((greenComponent > redComponent) && (greenComponent > blueComponent)) {
                    finalPixel = FILL_VALUE;
                }
                outputImage[row][col] = finalPixel;
            }
        }
        return outputImage;
    }
    /**
     * A wild and mysterious image transform: Swap color blur to red, red to green, green to blue.
     * <p>
     * You are free to implement this in any way you want. It is not tested rigorously by the test
     * suite, but it should do something (change the original image) and <i>not modify the original
     * image</i>.
     * <p>
     * Call this function mystery. It should take only the original image as a single argument and
     * return a modified image.
     *
     * @param originalImage the image to perform a strange and interesting transform on
     * @return the image transformed in wooly and frightening ways
     */
    public static int[][] mystery(final int[][] originalImage) {
        int[][] outputImage = new int[originalImage.length][originalImage[0].length];
        for (int row = 0; row < originalImage.length; row++) {
            for (int col = 0; col < originalImage[0].length; col++) {
                int finalPixel = originalImage[row][col];
                int redComponent = finalPixel & C1_VALUE;
                int greenComponent = (finalPixel & C3_VALUE) >> EIGHT;
                int blueComponent = (finalPixel & C5_VALUE) >> SIXTEEN;
                outputImage[row][col] =
                        (finalPixel & ALPHA_SHIFT) | (greenComponent << SIXTEEN)
                        | (redComponent << EIGHT) | blueComponent;
            }
        }
        return outputImage;
    }
    /**
     *  Color shifts a select portion of the original image, defined by the boundaries.
     * @param originalImage to be modified.
     * @param left boundary
     * @param right boundary
     * @param bottom boundary
     * @param top boundary
     * @param r shift value
     * @param g shift value
     * @param b shift value
     * @param a shift value
     * @return modified image
     */
    public static int[][] colorShiftSelect(final int[][] originalImage,
                                           final int left,
                                           final int right,
                                           final int bottom,
                                           final int top,
                                           final int[] rgba) {
        int r = rgba[0];
        int g = rgba[1];
        int b = rgba[2];
        int a = rgba[3];
        int[][] shiftImage = originalImage;
        int[][] modifiedImage = originalImage;
        if ((left >= right)
            || (bottom >= top)
            || (left < 0)
            || (bottom < 0)
            || (right > originalImage.length)
            || (top > originalImage[0].length)) {
            return originalImage;
        }
        if (r > 0) {
            shiftImage = moreRed(shiftImage, r);
        } else {
            shiftImage = lessRed(shiftImage, -1 * r);
        }
        if (g > 0) {
            shiftImage = moreGreen(shiftImage, g);
        } else {
            shiftImage = lessGreen(shiftImage, 1 * g);
        }
        if (b > 0) {
            shiftImage = moreBlue(shiftImage, b);
        } else {
            shiftImage = lessBlue(shiftImage, -1 * b);
        }
        if (a > 0) {
            shiftImage = moreAlpha(shiftImage, a);
        } else {
            shiftImage = lessAlpha(shiftImage, -1 * a);
        }
        for (int row = left; row < right; row++) {
            for (int col = bottom; col < top; col++) {
                modifiedImage[row][col] = shiftImage[row][col];
            }
        }
        return modifiedImage;
    }
    /**
     * Sorts all of the pixels in the original image from lowest to highest numerical value.
     * @param originalImage to be modified.
     * @return modified, sorted image.
     */
    public static int[][] sortedPixels(final int[][] originalImage) {
        int[][] output = new int[originalImage.length][originalImage[0].length];
        int[] pixelArray = new int[originalImage.length * originalImage[0].length];
        for (int row = 0; row < output.length; row++) {
            for (int col = 0; col < output[0].length; col++) {
                int pos = (row * output[0].length) + col;
                pixelArray[pos] = originalImage[row][col];
            }
        }
        Arrays.sort(pixelArray);
        for (int i = 0; i < pixelArray.length; i++) {
            int row = i / output[0].length;
            int col = i % output[0].length;
            output[row][col] = pixelArray[i];
        }
        return output;
    }
    /**
     * Returns the color value of a specific pixel.
     * @param originalImage to be sampled.
     * @param row of the pixel
     * @param col of the pixel
     * @return the color of the individual pixel.
     */
    public static int eyedropper(final int[][] originalImage, final int row, final int col) {
        return originalImage[row][col];
    }
    /**
     * 1/9, the square blur coefficient that makes each pixel the average of surrounding pixs.
     */
    static final double BLUR = 0.1111;
    /**
     *  Adds a simple square blur to an image.
     * @param originalImage to be blurred
     * @return blurred image.
     */
    public static int[][] blur(final int[][] originalImage) {
        int[][] output = new int[originalImage.length][originalImage[0].length];
        for (int row = 1; row < originalImage.length - 1; row++) {
            for (int col = 1; col < originalImage[0].length - 1; col++) {
                double pix = (BLUR * originalImage[row][col])
                          + (BLUR * originalImage[row - 1][col - 1])
                          + (BLUR * originalImage[row - 1][col])
                          + (BLUR * originalImage[row - 1][col + 1])
                          + (BLUR * originalImage[row][col - 1])
                          + (BLUR * originalImage[row][col + 1])
                          + (BLUR * originalImage[row + 1][col - 1])
                          + (BLUR * originalImage[row + 1][col])
                          + (BLUR * originalImage[row + 1][col + 1]);
                output[row][col] = (int) pix;
            }
        }
        return output;
    }
    public static int[][] blackAndWhite(final int[][] originalImage) {
        int[][] output = new int[originalImage.length][originalImage[0].length];
        for(int row = 0; row < originalImage.length; row++) {
            for(int col = 0; col < originalImage[0].length; col++) {
                output[row][col] =
            }
        }
        return output;
    }
}
