package com.example.dell.udemyapp79mapsandspeech.Model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class CountryDataSource {
    public static final  String COUNTRY_KEY = "country";
    public static final  float MINIMUM_CONFIDENCE_LEVEL = 0.4f;
    public static final String DEFAULT_COUNTRY_NAME = "Canada";
    public static final Double DEFAULT_COUNTRY_LATITUDE = 59.969151;
    public static final Double DEFAULT_COUNTRY_LONGITUDE = -111.459050;
    public static final String DEFAULT_MESSAGE = "Welcome ! ";

    private Hashtable<String,String> countriesAndMessages;

    public CountryDataSource(Hashtable<String,String> countriesAndMessages){
        this.countriesAndMessages = countriesAndMessages;
    }

    public String matchWithMinimumConfidenceLevelofUserWords(ArrayList<String> userWord,
                                                             float[] confidenceLevel) {
        //first we need to find if the arguments we are passing to this method are null or not
        if (userWord == null && confidenceLevel == null) {
            return DEFAULT_COUNTRY_NAME;
        }
        //assign the size of the elements in the arraylist
        int numberOfUserWords = userWord.size();

        //Enumeration -  public interface
        //java.util.enumeration
        //An object that implements the Enumeration interface generates a series of
        //elements, one at a time.
        //successive calls to the nextElement method returns successive elements of the series

        Enumeration<String> countries;
        for (int index = 0; index < numberOfUserWords && index < confidenceLevel.length;
             index++) {
            if (confidenceLevel[index] < MINIMUM_CONFIDENCE_LEVEL) {
                break;
            }
            String acceptedUserWord = userWord.get(index);
            countries = countriesAndMessages.keys();

            while (countries.hasMoreElements()) {
                String selectedCountry = countries.nextElement();
                if (acceptedUserWord.equalsIgnoreCase(selectedCountry)){
                    return acceptedUserWord;
                }
            }
        }
        return DEFAULT_COUNTRY_NAME;
    }
    public String getInfoOfTheCountry(String country){
        return countriesAndMessages.get(country);
    }
}
