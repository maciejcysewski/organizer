package pl.jcode.organizer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import pl.jcode.organizer.Main;
import pl.jcode.organizer.domain.Event;

public class MainWindowController {

	private String newDate = null;

	@FXML
	private DatePicker datePicker;

	@FXML
	private Button saveButton;

	@FXML
	private Label hourField;

	@FXML
	private Label descField;

	public void getDatePicker() {
		newDate = datePicker.getValue().toString();
		System.out.println(newDate);
	}

	public void saveEvent() {
		
		if (newDate != null) {
			if (hourField.getText() != null) {
				if (descField.getText() != null) {

					//add event to database
					Event event = new Event();
					event.setDate(newDate);
					event.setHour("12:00");
					event.setEvent("film w kinie");
					Main.entityManager.getTransaction().begin();
					Main.entityManager.persist(event);
					Main.entityManager.getTransaction().commit();

				} else {
					System.out.println("Opisz wydarzenie!");
				}
			} else {
				System.out.println("Podaj godzinê!");
			}
		} else {
			System.out.println("Proszê wybraæ datê!");
		}
		
		
		
		
		
	}

}
