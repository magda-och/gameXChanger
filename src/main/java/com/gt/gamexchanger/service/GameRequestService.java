package com.gt.gamexchanger.service;

import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.RequestGame;
import com.gt.gamexchanger.model.RequestGameDto;
import com.gt.gamexchanger.repository.GameRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameRequestService {
    private final GameRequestRepository gameRequestRepository;
    private final DtoMapper<RequestGameDto, RequestGame> dtoMapper;

    public GameRequestService(GameRequestRepository gameRequestRepository, DtoMapper<RequestGameDto, RequestGame> dtoMapper) {
        this.gameRequestRepository = gameRequestRepository;
        this.dtoMapper = dtoMapper;
    }
    public RequestGameDto addGameRequest(RequestGameDto requestGameDto) {
        var requestGame = dtoMapper.toDomainObject(requestGameDto);
        gameRequestRepository.addAndSendNewGameRequest(requestGame);
        return dtoMapper.toDto(requestGame);
    }
    public List<RequestGameDto> getAllRequest() {
        return gameRequestRepository.getAllRequest().stream()
                .map(dtoMapper::toDto)
                .toList();
    }

    public List<RequestGameDto> getSendGameRequest(long userId) {
        return gameRequestRepository.getAllRequest().stream()
                .filter((RequestGame p) -> p.getFromUserId()==userId)
                .map(dtoMapper::toDto)
                .toList();
    }
    public List<RequestGameDto> getReceivedGameRequest(long userId) {
        return gameRequestRepository.getAllRequest().stream()
                .filter((RequestGame p) -> p.getToUserId()==userId)
                .map(dtoMapper::toDto)
                .toList();
    }

    public void updateGame(UUID requestId, RequestGameDto requestGameDto) {
        var requestGameOptional = getRequestById(requestId);
        if (requestGameOptional.isPresent()) {
            var requestGame = requestGameOptional.get();
            updateRequestStatus(requestGameDto, requestGame);
        }
    }
    private RequestGame updateRequestStatus(RequestGameDto requestGameDto, RequestGame requestGame) {
        if (requestGameDto.getRequestStatus() != null) {
            requestGame.setRequestStatus(requestGameDto.getRequestStatus());
        }
        return requestGame;
    }

    public boolean removeGameRequestById(UUID gameRequestId) {
        if (getRequestById(gameRequestId).isPresent()) {
            gameRequestRepository.removeGameRequestById(gameRequestId);
            return true;
        }
        return false;
    }
    private Optional<RequestGame> getRequestById(UUID requestId) {
        return Optional.ofNullable(gameRequestRepository.getRequestById(requestId));
    }

}
