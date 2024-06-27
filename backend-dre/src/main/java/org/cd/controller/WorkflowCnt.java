package org.cd.controller;

import jakarta.ws.rs.Path;

@Path("/workflow")
public class WorkflowCnt {
//    COMMENTENA ALOHA RETO

//    @Inject
//    ProcessEngine processEngine;
//    @Inject
//    public RuntimeService runtimeService;
//
//    @POST
//    @Path("/start")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String startWorkflow() {
//        try {
//            ProcessInstance processInstance = processEngine.getRuntimeService()
//                    .startProcessInstanceByKey("SimpleWorkflow");
//            return "Workflow démarré avec l'ID : " + processInstance.getId();
//        } catch (Exception e) {
//            return "Erreur lors du démarrage du workflow : " + e.getMessage();
//        }
//    }
//
//    @GET
//    @Path("/start-process")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String startProcessInstance() {
//        String processInstanceId = runtimeService.startProcessInstanceByKey("process").getId();
//        return "Process instance with id " + processInstanceId + " started!";
//    }

}
