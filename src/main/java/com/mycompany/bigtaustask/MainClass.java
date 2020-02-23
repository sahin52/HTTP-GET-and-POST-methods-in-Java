/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bigtaustask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author sahin kasap
 */
public class MainClass {
    
    public static JSONObject HttpGetRequestToJsonObject(String url) throws MalformedURLException, IOException{
        JSONObject res;
        URL u;
        StringBuffer sb;   
        u = new URL(url);
        HttpURLConnection connection;
        connection = (HttpURLConnection) u.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String in;
        sb = new StringBuffer();
        while((in=bufferedReader.readLine())!=null){
            sb.append(in);
        }
        res = new JSONObject(sb.toString());
        connection.disconnect();
        return res;
    }
    public static JSONArray HttpGetRequestToJsonArray(String url) throws MalformedURLException, IOException{
        JSONArray res;
        URL u;
        StringBuffer sb;
        
            
        u = new URL(url);
        HttpURLConnection connection;
        connection = (HttpURLConnection) u.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String in;
        sb = new StringBuffer();
        while((in=bufferedReader.readLine())!=null){
            sb.append(in);
        }
        System.out.println(sb.toString());
        res = new JSONArray(sb.toString());
        connection.disconnect();
        return res;
    }
    private static JSONObject postDataToUrlAndGetJSONObject(String url, String data) throws MalformedURLException, ProtocolException, IOException {
        JSONObject res;
        URL Url = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        osw.write(data);
        osw.close();
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        connection.getInputStream();
        String in;
        StringBuffer sb = new StringBuffer();
        while((in=bufferedReader.readLine())!=null){
            sb.append(in);
        }
        res = new JSONObject(sb.toString());
        connection.disconnect();
        return res;
    }
    
    public static boolean StudentIdCheck(String Id){
        if(Id.length()>7 || Id.length()<7) 
            return false;
        return true;
    }
    public static void postDataToUrl(String url, String data) throws MalformedURLException, ProtocolException, IOException{
        JSONObject res;
        URL Url = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        osw.write(data);
        osw.close();
        connection.disconnect();
    }
    
    
    
    
    
    
    
    
    
    public static void main(String args[]) throws ProtocolException, MalformedURLException, IOException{
        try {
            postDataToUrl("http://httpbin.org/post", "somedata=somedataa");
            System.out.println("POST method\n" + postDataToUrlAndGetJSONObject("http://httpbin.org/post", "somedata=somedataa").toString());
            
        } catch(Exception ioe) {
            System.out.println("Error in POST methods");
            ioe.printStackTrace();
        }
        try{
            System.out.println("GET method\n" + HttpGetRequestToJsonObject("http://data.fixer.io/api/latest") );
        }
        catch(Exception ex){
            System.out.println("Error in GET method");
            System.out.println(ex);
        }
    }
}
