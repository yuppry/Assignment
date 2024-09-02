import logo from './logo.svg';
import './App.css';
import SearchHeader from './Components/SearchHeader';
import HeaderColumn from './Components/HeaderColumn';


export default function App() {
  return(
    <div className="App">
      <SearchHeader />
      <HeaderColumn />
      {/* Other components */}
    </div>
  )
}

