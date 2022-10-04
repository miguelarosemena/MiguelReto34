/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ciclo3.motorent.service;

import com.ciclo3.motorent.model.Category;
import com.ciclo3.motorent.model.Motorbike;
import com.ciclo3.motorent.repository.MotorbikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorbikeService {
    @Autowired
    private MotorbikeRepository MotorbikeRepository;

    public List<Motorbike> getAll(){
        return MotorbikeRepository.getAll();
    }
    public Optional<Motorbike> getMotorbike(int idMoto){
        return MotorbikeRepository.getMotorbike(idMoto);
    }
    public Motorbike save(Motorbike p){
        if(p.getIdMoto()==null){
            return MotorbikeRepository.save(p);
        }else{
            Optional<Motorbike> e = MotorbikeRepository.getMotorbike(p.getIdMoto());
            if(e.isPresent()){
                return p;
            }else{
                return MotorbikeRepository.save(p);
            }
        }
    }
    public Motorbike update(Motorbike p){
        if(p.getIdMoto()!=null){
            Optional<Motorbike> q = MotorbikeRepository.getMotorbike(p.getIdMoto());
            if(q.isPresent()){
                if(p.getBrand()!=null){
                    q.get().setName(p.getBrand());
                }
                if(p.getModel()!=null){
                    q.get().setModel(p.getModel());
                }
                if(p.getName()!=null){
                    q.get().setName(p.getName());
                }
                if(p.getDescription()!=null){
                    q.get().setName(p.getDescription());
                }

                MotorbikeRepository.save(q.get());
                return q.get();
            }else{
                return p;
            }
        }else{
            return p;
        }
    }
    public boolean delete(int idMoto){
        boolean flag=false;
        Optional<Motorbike>p= MotorbikeRepository.getMotorbike(idMoto);
        if(p.isPresent()){
            MotorbikeRepository.delete(p.get());
            flag=true;
        }
        return flag;

    }
}
