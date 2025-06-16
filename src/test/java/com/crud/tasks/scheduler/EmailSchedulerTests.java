package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailSchedulerTests {
    @InjectMocks
    private EmailScheduler emailScheduler;
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private AdminConfig adminConfig;


    @Test
    void testSendInformationEmail() {
        // Given
        when(taskRepository.count()).thenReturn(2L);

        ArgumentCaptor<Mail> mailCaptor = ArgumentCaptor.forClass(Mail.class);

        // When
        emailScheduler.sendInformationEmail();

        // Then
        verify(simpleEmailService, times(1)).send(mailCaptor.capture());
        Mail capturedMail = mailCaptor.getValue();

        assertThat(capturedMail.getMessage()).isEqualTo("Currently in database you got: 2 tasks");
    }
}