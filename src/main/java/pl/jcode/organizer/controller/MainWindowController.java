package pl.jcode.organizer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pl.jcode.organizer.Main;
import pl.jcode.organizer.domain.Event;

public class MainWindowController {

	private String newDate = "";
	private String newHour = "";
	private String newDesc = "";

	@FXML
	private DatePicker datePicker;

	@FXML
	private Button saveButton;

	@FXML
	private TextField hourField;

	@FXML
	private TextField descField;

	public void getDatePicker() {
		try {
			newDate = datePicker.getValue().toString();
			System.out.println(newDate);
		} catch (Exception e) {
			newDate = "";
			System.out.println("Wyst¹pi³ wyj¹tek (data)\n" + e);
		}
	}

	public void saveEvent() {
		try {
			newHour = hourField.getText();
		} catch (Exception e) {
			newHour = "";
			System.out.println("Wyst¹pi³ wyj¹tek (godzina)\n" + e);
		}
		try {
			newDesc = descField.getText();
		} catch (Exception e) {
			newDesc = "";
			System.out.println("Wyst¹pi³ wyj¹tek (event)\n" + e);
		}

		if (newDate.equals("")) {
			System.out.println("Proszê wybraæ datê!");
		} else {
			if (newHour.equals("")) {
				System.out.println("Podaj godzinê!");
			} else {
				if (newDesc.equals("")) {
					System.out.println("Opisz wydarzenie!");
				} else {
					// add event to database
					Event event = new Event();
					event.setDate(newDate);
					event.setHour(newHour);
					event.setEvent(newDesc);
					Main.entityManager.getTransaction().begin();
					Main.entityManager.persist(event);
					Main.entityManager.getTransaction().commit();

					// show result on ListView
					// showEventList(newDate);

					// clear all fields
					hourField.setText(null);
					descField.setText(null);
					newHour = null;
					newDesc = null;
				}
			}
		}

	}

}
