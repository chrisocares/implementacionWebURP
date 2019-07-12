package com.cocaresh.function.email;

import java.util.Optional;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {

	@FunctionName("HttpTrigger-Java")
	public HttpResponseMessage run(
	        @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, route="{tokenRoute}", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
	        @BindingName("tokenRoute")String route,
	        final ExecutionContext context) {
	    sendMessage();
	    return request.createResponseBuilder(HttpStatus.OK).build();
	}
	
	public void sendMessage() {
		Mail mail = new Mail();
		Email fromEmail = new Email();
		fromEmail.setName("Christopher");
		fromEmail.setEmail("chrisocares@gmail.com");
		mail.setFrom(fromEmail);
		mail.setSubject("Function");
		Personalization personalization = new Personalization();
		Email toEmail = new Email();
		toEmail.setName("Function");
		toEmail.setEmail("chrisocares@gmail.com");
		personalization.addTo(toEmail);
		personalization.addDynamicTemplateData("subject", "Adopcion mascota");
		personalization.addDynamicTemplateData("nombre", "Christopher");
		personalization.addDynamicTemplateData("nombreMascota", "Valentino");
		personalization.addDynamicTemplateData("edad", "2 años");
		personalization.addDynamicTemplateData("sexo", "Masculino");
		personalization.addDynamicTemplateData("raza", "schnauzer");
		personalization.addDynamicTemplateData("monto", "50.00");
		personalization.addDynamicTemplateData("nroCtaAbono", "3193021-2123-213239");
		mail.addPersonalization(personalization);
		mail.setTemplateId("d-d628354a55c14d06b28c1d833584378d");
		SendGrid sendGrid = new SendGrid("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");
		sendGrid.addRequestHeader("X-Mock", "true");
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			sendGrid.api(request);
		} catch (Exception e) {
		}
	}
}
