package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CalcBB {
	private String x;
	private String y;
        private String op;
	private Double result;

	@Inject
	FacesContext ctx;
	
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}
        public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public Double getResult() {
		return result;
	}

	public String calc() {
		try {
			double x = Double.parseDouble(this.x);
			double y = Double.parseDouble(this.y);
                        double op = Double.parseDouble(this.op) / 100;
                        double i = op / 12;

			double rata =  x * (i * Math.pow(1 + i, y)) / (Math.pow(1 + i, y) - 1);
                        result = Math.floor(rata * 100)/100;
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return "showresult"; 
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return null; 
		}
				
	}

	public String info() {
		return "info"; 
	}
}