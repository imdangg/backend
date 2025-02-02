import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from "./components/Home.jsx";
import MyExchange from './components/MyExchange.jsx';
import MyInsight from './components/MyInsight.jsx';
import InsightDetail from './components/InsightDetail.jsx'

export default function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/my-exchange" element={<MyExchange />} />
                <Route path="/my-insight" element={<MyInsight />} />
                <Route path="/insight/:id" element={<InsightDetail />} />
            </Routes>
        </Router>
    );
}
