package com.cusyas.android.paycheck.billDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BillViewModel(application: Application) : AndroidViewModel(application){

    private val repository: BillRepository

    val allBills: LiveData<List<Bill>>

    init {
        val billsDao = BillRoomDatabase.getDatabase(application).billDao()
        repository = BillRepository(billsDao)
        allBills = repository.allBills
    }

    fun insert(bill: Bill) = viewModelScope.launch {
        repository.insert(bill)
    }

    fun getUnpaidBills():List<Bill>{
        return repository.getUnpaidBills()
    }

    fun loadById(billId: Int):LiveData<Bill>{
        return repository.loadById(billId)
    }

    fun loadAllByIds(billId: IntArray):LiveData<List<Bill>>{
        return repository.loadAllByIds(billId)
    }

    fun updateBill(bill: Bill) = viewModelScope.launch{
        repository.updateBill(bill)
    }

    fun deleteBill(bill: Bill) = viewModelScope.launch {
        repository.deleteBill(bill)
    }

    fun resetAllPaid(){
        GlobalScope.launch {
            repository.resetAllPaid()
        }
    }

}