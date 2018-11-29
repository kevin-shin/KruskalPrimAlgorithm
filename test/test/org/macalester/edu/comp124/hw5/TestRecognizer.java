package org.macalester.edu.comp124.hw5;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by bjackson on 11/1/2016.
 */
public class TestRecognizer {

    private Recognizer recognizer;
    private List<Point> originalPoints;

    private static final int ORIGINAL_N = 20;

    @Before
    public void setup(){
        recognizer = new Recognizer();
        originalPoints = new ArrayList<>(ORIGINAL_N);
        for(int i=0; i < ORIGINAL_N; i++){
            originalPoints.add(new Point(i, 0));
        }
    }

    /**
     * Tests that points are resampled correctly
     */
    @Test
    public void testResample(){

        int n = 10;
        List<Point> resampled = recognizer.resample(originalPoints, n);
        assertEquals(n, resampled.size()); // resampling should return the correct number of points

        double interval = (ORIGINAL_N-1.0)/(n-1.0); //Path length is 19, so interval should be 19/(n-1) with n=10;

        Iterator<Point> it = resampled.iterator();
        double i=0;
        while (it.hasNext()){
            Point point = it.next();
            assertEquals(i, point.getX(), 0.01);
            assertEquals(0, point.getY(), 0.01);
            i+=interval;
        }
    }

//    /**
//     * Tests the path length.
//     */
//    @Test
//    public void testPathLength(){
//        assertEquals(ORIGINAL_N-1, recognizer.pathLength(originalPoints), 0.0001);
//        assertEquals(ORIGINAL_N, originalPoints.size());
//    }
//
//    /**
//     * Tests that the indicative angle (the angle needed to rotate the first point around the centroid to line up with the positive x axis)
//     * is correct. With points (0,0) through (19,0) the first point is on the x axis but to the left of the centroid (-x axis ) so it must rotate by pi.
//     */
//    @Test
//    public void testIndicativeAngle(){
//        double angle = recognizer.indicativeAngle(originalPoints);
//        assertEquals(0.0, angle, 0.001);
//        assertEquals(ORIGINAL_N, originalPoints.size());
//
//        List<Point> reversed = new ArrayList<>(originalPoints.size());
//        reversed.addAll(originalPoints);
//        Collections.reverse(reversed);
//
//        angle = recognizer.indicativeAngle(reversed);
//        assertEquals(Math.PI, angle, 0.001);
//    }
//
//    /**
//     * Tests rotation.
//     * The gesture starts at the points (0,0) to (19,0). When rotated by pi around the centroid the order should reverse.
//     */
//    @Test
//    public void testRotateBy(){
//        List<Point> rotated = recognizer.rotateBy(originalPoints, Math.PI);
//
//        assertEquals(ORIGINAL_N, originalPoints.size());
//        assertEquals(new Point(0,0), originalPoints.get(0));
//        Iterator<Point> it = rotated.iterator();
//        double i=ORIGINAL_N-1.0;
//        while (it.hasNext()){
//            Point point = it.next();
//            assertEquals(i, point.getX(), 0.001);
//            assertEquals(0, point.getY(), 0.001);
//            i-=1.0;
//        }
//
//    }
//
//    /**
//     * Tests scaling by creating a 100 by 100 size box and scaling it to 200 by 200
//     */
//    @Test
//    public void testScaleTo(){
//        List<Point> box = new ArrayList<>(4);
//        box.add(new Point(0,0));
//        box.add(new Point(100, 0));
//        box.add(new Point(100,100));
//        box.add(new Point(0, 100));
//        List<Point> scaled = recognizer.scaleTo(box, 200);
//
//
//        assertEquals(4, box.size());
//        assertEquals(new Point(0,0), scaled.get(0));
//        assertEquals(new Point(200,0), scaled.get(1));
//        assertEquals(new Point(200,200), scaled.get(2));
//        assertEquals(new Point(0,200), scaled.get(3));
//    }
//
//    /**
//     * Tests that translating the points moves the centroid to the indicated point.
//     */
//    @Test
//    public void testTranslateTo(){
//        List<Point> translated = recognizer.translateTo(originalPoints, new Point(0.0,0.0));
//        assertEquals(ORIGINAL_N, originalPoints.size());
//        assertEquals(new Point(0,0), originalPoints.get(0));
//
//        Iterator<Point> it = translated.iterator();
//        double i=-(ORIGINAL_N-1.0)/2.0;
//        while (it.hasNext()){
//            Point point = it.next();
//            assertEquals(i, point.getX(), 0.001);
//            assertEquals(0, point.getY(), 0.001);
//            i+=1.0;
//        }
//    }
//
//    //TODO: Test centroid and boundingBox methods
//
//    /**
//     * Test the recognition and scoring
//     */
//    @Test
//    public void testRecognize(){
//        //canvas.getWindowFrame().dispose();
//        IOManager ioManager = new IOManager();
//        List<Point> templateGesture = ioManager.loadGesture("arrowTemplate.xml");
//        List<Point> circleTemplate = ioManager.loadGesture("circleTemplate.xml");
//        //TODO: Add gestures as templates in your recognizer
//
//
//        List<Point> testGesture = ioManager.loadGesture("arrowTest.xml");
//        //TODO: Recognize the testGesture against the template Gestures.
//
//        //TODO: set score to the recognition score.
//        double score = 0;
//
//        assertEquals(0.888684, score, 0.001); // testGesture should match against templateGesture with a score of 0.88
//        // If you get 0.89 you are likely rotating by the positive indicative angle rather than the correct negative angle.
//
//    }
}
