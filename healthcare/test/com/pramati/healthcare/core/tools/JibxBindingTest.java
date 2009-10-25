package com.pramati.healthcare.core.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.schema.codegen.Item;

import com.pramati.healthcare.model.ContactType;
import com.pramati.healthcare.model.Course;
import com.pramati.healthcare.model.Episodicity;
import com.pramati.healthcare.model.Name;
import com.pramati.healthcare.model.Onset;
import com.pramati.healthcare.model.Patient;
import com.pramati.healthcare.model.Problem;

public class Test
{
    /**
     * Unmarshal the sample document from a file, then marshal it back out to
     * another file.
     * 
     * @param args 
     */
	public static void main(String[] args) {
//        if (args.length < 2) {
//            System.out.println("Usage: java -cp ... " +
//                "org.jibx.starter2.Test in-file out-file");
//            System.exit(0);
//        }
		try {
            
            // unmarshal customer information from file
            IBindingFactory bfact = BindingDirectory.getFactory(Name.class);
            //IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
            //FileInputStream in = new FileInputStream("binding.xml");
           // Name name = (Name)uctx.unmarshalDocument(in, null);
            
           
            Name name = new Name();
            name.setFirstName("Santhosh");
            name.setLastName("Gandhe");
            name.setMiddleName("hiehllo");
            Problem prob = new Problem();
            prob.setCourse(Course.ACUTE); 
            prob.setDescription("sanDesc");
            prob.setEpisodicity(Episodicity.NEW);
            prob.setOnset(Onset.ACUTE);
            
			// marshal object back out to file (with nice indentation, as UTF-8)
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(2);
			FileOutputStream out = new FileOutputStream("binding-out.xml");
			mctx.setOutput(out, null);
			mctx.marshalDocument(prob);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
            System.exit(1);
		} catch (JiBXException e) {
			e.printStackTrace();
            System.exit(1);
		}
	}
}