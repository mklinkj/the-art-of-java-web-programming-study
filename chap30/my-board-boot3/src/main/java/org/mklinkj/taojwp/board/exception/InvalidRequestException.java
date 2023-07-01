package org.mklinkj.taojwp.board.exception;

import lombok.Getter;
import org.mklinkj.taojwp.common.domain.ModalMessage;

public class InvalidRequestException extends RuntimeException {
  @Getter private final ModalMessage modalMessage;

  public InvalidRequestException(ModalMessage modalMessage) {
    super(modalMessage.getTitle());
    this.modalMessage = modalMessage;
  }
}
