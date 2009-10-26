package com.pramati.healthcare.core.tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;

import com.pramati.healthcare.model.Contact;
import com.pramati.healthcare.model.ContactType;
import com.pramati.healthcare.model.Course;
import com.pramati.healthcare.model.Demography;
import com.pramati.healthcare.model.Episodicity;
import com.pramati.healthcare.model.Name;
import com.pramati.healthcare.model.Onset;
import com.pramati.healthcare.model.Patient;
import com.pramati.healthcare.model.Problem;

public class JibxBindingTest
{
    /**
     * Marshal given Data model, provided its schema 
     * and jibx bindings are already generated
     * 
     * @param args 
     */
	public static void main(String[] args) {

		try {
                        
            IBindingFactory bfact = BindingDirectory.getFactory(Patient.class);        
                      
            Name name = new Name();
            name.setFirstName("Santhosh");
            name.setLastName("Gandhe");
            name.setMiddleName("");
            Problem prob = new Problem();
            prob.setCourse(Course.ACUTE); 
            prob.setDescription("description");
            prob.setEpisodicity(Episodicity.NEW);
            prob.setOnset(Onset.ACUTE);
            Demography demography = new Demography();
            Contact contacts = new Contact();
            contacts.setContact(ContactType.EMAIL, "hi@yaho.com");
            contacts.setContact(ContactType.EMERGENCY_CONTACT, "9888788765");
            demography.setContact(contacts);
            Patient patient = new Patient();
            patient.setProblem(prob);
            patient.setDemography(demography);
			// marshal object out to file (with nice indentation, as UTF-8)
			IMarshallingContext mctx = bfact.createMarshallingContext();
			mctx.setIndent(2);
			FileOutputStream out = new FileOutputStream("binding-out.xml");
			mctx.setOutput(out, null);
			mctx.marshalDocument(patient);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
            System.exit(1);
		} catch (JiBXException e) {
			e.printStackTrace();
            System.exit(1);
		}
	}
}