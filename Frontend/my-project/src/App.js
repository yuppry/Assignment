import logo from './logo.svg';
import React, { useState } from 'react';
import './App.css';
import SearchHeader from './Components/SearchHeader';
import HeaderColumn from './Components/HeaderColumn';


export default function App() {
  const [dateFrom, setDateFrom] = useState('');
  const [dateTo, setDateTo] = useState('');
  const [filteredCount, setFilteredCount] = useState(0);
  return(
    <div className="App">
      <SearchHeader dateFrom={dateFrom} 
        dateTo={dateTo} 
        setDateFrom={setDateFrom} 
        setDateTo={setDateTo} 
        filteredCount={filteredCount}/>

      <HeaderColumn dateFrom={dateFrom} 
        dateTo={dateTo} 
        setFilteredCount={setFilteredCount}/>
      
    </div>
  )
}


