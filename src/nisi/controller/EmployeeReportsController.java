/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nisi.controller;

import bd.uber.FXMLFilePath;
import bd.uber.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

/**
 * FXML Controller class
 *
 * @author Jannatul Ferdous
 */
public class EmployeeReportsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void employeeReportButtonOnClick(MouseEvent event) {
//        try{
//           
//            FileChooser fc = new FileChooser();
//            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
//            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.bmp", "*.png"));
//            File f = fc.showSaveDialog(null);
//            if(f!=null){              
//                PdfWriter pw = new PdfWriter(new FileOutputStream(f));
//                //PdfWriter pw = new PdfWriter(new FileOutputStream("testPDF.pdf"));
//                PdfDocument pdf =  new PdfDocument(pw);
//                pdf.addNewPage();
//                Document doc = new Document(pdf) {
//                    @Override
//                    public int getLength() {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public void addDocumentListener(DocumentListener dl) {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public void removeDocumentListener(DocumentListener dl) {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public void addUndoableEditListener(UndoableEditListener ul) {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public void removeUndoableEditListener(UndoableEditListener ul) {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public Object getProperty(Object o) {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public void putProperty(Object o, Object o1) {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public void remove(int i, int i1) throws BadLocationException {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public void insertString(int i, String string, AttributeSet as) throws BadLocationException {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public String getText(int i, int i1) throws BadLocationException {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public void getText(int i, int i1, Segment sgmnt) throws BadLocationException {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public Position getStartPosition() {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public Position getEndPosition() {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public Position createPosition(int i) throws BadLocationException {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public Element[] getRootElements() {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public Element getDefaultRootElement() {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//
//                    @Override
//                    public void render(Runnable r) {
//                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//                    }
//                };
//                doc.setLeftMargin(70);
//                //----------------------------------------------------
//                //adding paragrapg to the pdf
//                String newline = "\n";
//                Paragraph lineSpace = new Paragraph(newline);
//                lineSpace.setHeight(8);
//                
//                String paraText1 
//                        = "This is line 1 of the paragraph, and the line continue....\n"
//                        + "This is line 2 of the paragraph, and the line continue....\n"
//                        + "This is line 3 of the paragraph, and the line continue....\n"
//                        + "This is line 4 of the paragraph, and the line continue....\n";
//                Paragraph para1 = new Paragraph(paraText1);
//                
//                Text titleText = new Text("This is the TITLE of the pdf");
//                titleText.setFontSize(18f);
//                Paragraph pageTitle = new Paragraph(titleText);
//                pageTitle.setBold();    //OR titleText.setBold();
//
//                PdfFont font2 = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
//                PdfFont fontBold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
//                Text title = new Text("Thinking in Java (4th Ed)").setFont(fontBold);
//                Text author = new Text("Bruce Eckel").setFont(font2);
//                author.setFontColor(Color.RED);
//                Paragraph bookInfo = new Paragraph().add(title).add(" -- by ").add(author);
//                
//                doc.add(pageTitle);
//                doc.add(lineSpace);
//                doc.add(para1);
//                doc.add(lineSpace);
//                doc.add(bookInfo);
//                doc.add(lineSpace);
//                
//                //----------------------------------------------------
//                
//                //adding a list
//                List progLangList = new List();
//                progLangList.add("C");
//                progLangList.add("C++");
//                progLangList.add("Java");
//                progLangList.add("Python");
//                progLangList.add("C#");          
//                doc.add(progLangList);
//                doc.add(lineSpace);
//                
//                //----------------------------------------------------
//                //adding an image
//                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
//                a.setContentText("Do you want to add an Image?");
//                Optional<ButtonType> result = a.showAndWait();
//                if(result.get() == ButtonType.OK){ 
//                    File imageFile = fc.showOpenDialog(null);
//                    String imagePath = imageFile.getAbsolutePath();
//                    ImageData data = ImageDataFactory.create(imagePath);
//                    Image image = new Image(data);  
//                    image.setAutoScale(true);
//                    
//                    doc.add(image);
//                    doc.add(lineSpace);
//                    a = new Alert(Alert.AlertType.INFORMATION);
//                    a.setContentText("The image is added successfully.");
//                    a.showAndWait();
//                }
//                //----------------------------------------------------
//                
//                //adding table in pdf
//                float colWidthArr[] = {70f, 200f, 100f};
//                Table pdfTable = new Table(colWidthArr); 
//                
//                //adding header row
//                Cell cell_00 = new Cell(); 
//                cell_00.add("Product ID");
//                cell_00.setBackgroundColor(Color.YELLOW);
//                pdfTable.addCell(cell_00);
//                pdfTable.addCell(new Cell().setBackgroundColor(Color.YELLOW).add("Product Name"));
//                pdfTable.addCell(new Cell().setBackgroundColor(Color.YELLOW).add("Price"));
//                
//                //adding content row
//                pdfTable.addCell(new Cell().add("111"));
//                pdfTable.addCell(new Cell().add("Lux Soap"));
//                pdfTable.addCell(new Cell().add("34.0"));
//                
//                //adding content row
//                pdfTable.addCell(new Cell().add("222"));
//                pdfTable.addCell(new Cell().add("Aarong Yougart"));
//                pdfTable.addCell(new Cell().add("75.0"));
//                
//                //adding content row
//                pdfTable.addCell(new Cell().add("333"));
//                pdfTable.addCell(new Cell().add("Brown Bread"));
//                pdfTable.addCell(new Cell().add("85.0"));
//
//                doc.add(pdfTable);
//                
//                doc.close();
//                
//                a = new Alert(Alert.AlertType.INFORMATION);
//                a.setContentText("The PDF is saved successfully.");
//                a.showAndWait();    
//            }
//            else{
//                Alert a = new Alert(Alert.AlertType.INFORMATION);
//                a.setContentText("Saving as PDF: cancelled!");
//                a.showAndWait();               
//            }
//        }
//        catch(Exception e){
//            Alert a = new Alert(Alert.AlertType.INFORMATION);
//            a.setContentText("Oops! Exception: " + e.toString()+ " occured.");
//            a.showAndWait(); 
//            //System.out.println("Something went wrong...");
//            //System.out.println(e);
//        }                           
    }

    @FXML
    private void goBackButtonOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = Util.getInstance().getLoader(FXMLFilePath.HRM_Dashboard);
            Util.getInstance().showScene(
                    loader.load(),
                    event,
                    "HRM_Dashboard",
                    false
            );
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
