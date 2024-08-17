package RodionGraphicalLib;

import RodionANumbers.ADouble;
import RodionANumbers.ObservableADoubleValue;
import RodionANumbers.ADoubleChangeObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayedDouble implements ObservableADoubleValue {
    double number = 0,max,min;

    public ADouble value = ()-> number;
    public ADouble minSliderValue = ()->min;
    public ADouble maxSliderValue = ()->max;

    static int countOfWindows = 0;

    JButton currentValueButton,maxValueButton,minValueButton;
    RSlider mainSlider;
    List<ADoubleChangeObserver> observerList = new ArrayList<>();

    public DisplayedDouble(double minValue,double maxValue,double startValue, String description){
        number = startValue;
        min = minValue;
        max = maxValue;


        currentValueButton = new JButton("Current value "+ value.get());
        maxValueButton = new JButton("Max value "+maxSliderValue.get());
        minValueButton = new JButton("Min value "+minSliderValue.get());

        mainSlider = new RSlider(startValue/maxSliderValue.get()) {
            @Override
            void valueChanged(double value) {
                number = value*maxSliderValue.get();
                currentValueButton.setText("Current value "+String.format("%.1f", DisplayedDouble.this.value.get()));
                notifyObservers();
            }
        };

        new RJFrame(){
            {
                setTitle(description);
                setBounds(900,70*countOfWindows++,500,70);
                setLayout(new GridLayout(2,1));
                add(new JPanel(){
                    {
                        setLayout(new GridLayout(1,3));

                        add(minValueButton);
                        add(currentValueButton);
                        add(maxValueButton);
                    }
                });
                add(mainSlider);
            }
        };

    }

    public void set(double value){
        number = value;
        currentValueButton.setText("Current value "+String.format("%.3f", DisplayedDouble.this.value.get()));
        notifyObservers();
    }


    public void addObserver(ADoubleChangeObserver observer) {
        observerList.add(observer);
    }

    public void removeObserver(ADoubleChangeObserver observer) {
        observerList.remove(observer);
    }

    public void notifyObservers() {
       observerList.forEach(crtObserver->crtObserver.valueChanged());
    }
}
