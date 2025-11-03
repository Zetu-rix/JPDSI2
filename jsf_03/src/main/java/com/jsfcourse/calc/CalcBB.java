package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CalcBB {
	private Double x;
	private Double y;
        private Double op;
	private Double result;

	@Inject
	FacesContext ctx;
	
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getOp() {
		return op;
	}

	public void setOp(Double op) {
		this.op = op;
	}

	public Double getResult() {
		return result;
	}

	public String calc() {
		try {
                Double oprocentowanie = this.op / 100;
                Double i = oprocentowanie / 12;

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